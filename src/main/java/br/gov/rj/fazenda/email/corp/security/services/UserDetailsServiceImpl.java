package br.gov.rj.fazenda.email.corp.security.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.rj.fazenda.email.corp.security.jwt.JwtUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private JwtUtils jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			JSONObject obj = new JSONObject(username);
			String loginUsuario = (String) obj.get("loginUsuario");
			JSONArray arr = obj.getJSONArray("funcionalidades");
			
			Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
			String role;
			for (int i = 0; i < arr.length(); i++) {
				role = arr.getString(i);
				grantedAuthorities.add(new SimpleGrantedAuthority(role));
			}
			
			return UserDetailsImpl.build(loginUsuario, grantedAuthorities);
			
			}
		catch(Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}
	
	public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
		try {
			if (token == null) {
	            throw new UsernameNotFoundException("unknow");
	        }
			
			String username = jwtTokenUtil.getUsernameFromToken(token);
			
			Collection<? extends GrantedAuthority> roles = jwtTokenUtil.getRoles(token);
			Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
			for (GrantedAuthority roleAuthority : roles) {
				    String role = roleAuthority.getAuthority();
				    if (role != null) {
				    	role = role.replace("[", "");
				    	role = role.replace("]", "");
				    	role = role.replace("=", "");
				    	role = role.replace("{", "");
				    	role = role.replace("}", "");
				    	role = role.replace("authority", "");
				    	role = role.trim();
				    	grantedAuthorities.add(new SimpleGrantedAuthority(role));
				    }
			}
			
			return UserDetailsImpl.build(username, grantedAuthorities);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
