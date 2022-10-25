package br.gov.rj.fazenda.email.corp.security.resource;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.gov.rj.fazenda.email.corp.dto.JwtResponse;
import br.gov.rj.fazenda.email.corp.entity.TicketRequest;
import br.gov.rj.fazenda.email.corp.security.jwt.JwtUtils;
import br.gov.rj.fazenda.email.corp.security.services.UserDetailsImpl;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api")
public class JwtAuthenticationResource implements JwtAuthenticationResourceApi {

	@Value("${ssa.url}")
	private String ssaUrl;

	@Value("${ssa.secret}")
    private String ssaSecret;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtTokenUtil;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody TicketRequest authenticationRequest) throws Exception {
		try {
			String auth = authenticateSSA(authenticationRequest.getTicket());
			log.info("JSON SSA: " + auth);
			JSONObject obj = new JSONObject(auth);
			var loginUsuario = (String) obj.get("loginUsuario");
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(auth, "password"));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String jwt = jwtTokenUtil.generateJwtToken(authentication,authenticationRequest.getTicket());
			
			Date expiry = jwtTokenUtil.getExpiryFromJwtToken(jwt);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			
			log.info("JWT: " + jwt);
			log.info("login: " + loginUsuario);

			return ResponseEntity.ok(new JwtResponse(jwt, 
													 userDetails.getId(), 
													 userDetails.getUsername(),
													 roles,
													 expiry));
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	private String authenticateSSA(String ticket) throws BadCredentialsException, DisabledException  {
		try {
			final String authJSON = getAuth(ticket);
			if (authJSON.length() > 0) {
				if (!authJSON.contains("error")) {
					return authJSON;
				}
			}
			throw new BadCredentialsException("INVALID_CREDENTIALS", null);
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}

	public String getAuth(String ticket) {
		final String sign = doSign(ticket);
		final String sSsaResourceUrl = String.format(ssaUrl + "/ticket?tkt=%s&sgn=%s", ticket, sign);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(sSsaResourceUrl, String.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return "";
	}
    
    private String doSign(String ticket){
		final String HMAC_SHA_256 = "HmacSHA256";
		final String ENC_UTF_8 = "UTF-8";
		final String SECRET_STRING = ssaSecret;
	
    	byte[] hmac256Hash = null;
    	Mac mac;
		try {
			mac = Mac.getInstance(HMAC_SHA_256);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
        SecretKeySpec sks;
		try {
			sks = new SecretKeySpec(SECRET_STRING.getBytes(ENC_UTF_8), HMAC_SHA_256);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
        try {
			mac.init(sks);
		} catch (InvalidKeyException e) {
			return null;
		}
        try {
			hmac256Hash = mac.doFinal(ticket.getBytes(ENC_UTF_8));
		} catch (IllegalStateException | UnsupportedEncodingException e) {
			return null;
		}
        var sb = new StringBuilder();
        for (byte b : hmac256Hash) {
        	sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}