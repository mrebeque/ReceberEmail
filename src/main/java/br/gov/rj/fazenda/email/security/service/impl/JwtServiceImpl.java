package br.gov.rj.fazenda.email.security.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServlet

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.gov.rj.fazenda.email.security.client.AutenticadorClient;
import br.gov.rj.fazenda.email.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class JwtServiceImpl implements JwtService {
	public static final String ROLES = "role";
	
	@Autowired
	private AutenticadorClient authClient;

	@Override
	public Claims obterClaims(String token) throws ExpiredJwtException {
		String chaveAssinatura = authClient.getChaveJwt();
		return Jwts
				.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}

	@Override
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser()
			   .setSigningKey(authClient.getChaveJwt())
			   .parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;	
	}

	@Override
	public String obterLoginUsuario(String token) {
		return obterClaims(token)
				.getSubject();
	}
	
	@Override
	public Collection<GrantedAuthority> obterAuthorities(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		final Collection<GrantedAuthority> authorities =
                Arrays.stream(claims.get(ROLES).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());	
	      return authorities;
	}
	
	@Override
	public String obterAppl(String token) {
		return obterClaims(token)
				.get("aplicacao", String.class);
	}

	@Override
	public String obterTipoToken(String token) {
		return obterClaims(token)
				.get("tipoToken", String.class);
	}

	
	@Override
	public String extrairToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;		
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(authClient.getChaveJwt()).parseClaimsJws(token).getBody();
	}
}	
