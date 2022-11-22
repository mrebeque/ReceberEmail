package br.gov.rj.fazenda.email.security.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.rj.fazenda.email.security.service.JwtService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SecurityUserDetailsService implements UserDetailsService {
	
    @Autowired
    JwtService jwtSrv;
        
	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
	    try {
			String usuario = jwtSrv.obterLoginUsuario(token);		
			Collection<GrantedAuthority> roles = jwtSrv.obterAuthorities(token);
			
			if (usuario.isEmpty())
				throw (new UsernameNotFoundException("Usuário não cadastrado."));
			
			/* Aplicações usarão autenticação por barear token não será necessário 
			 * senha de usuário.
			 * 
			 */
			return User.builder()
					.username(usuario)
					.password(usuario+"123")
					.authorities(roles)
					.build();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new UsernameNotFoundException(e.getMessage());
		}
			
	}

	public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
		try {
			if (token == null) {
	            throw new UsernameNotFoundException("Token inválido.");
	        }
			
			String usuario = jwtSrv.obterLoginUsuario(token);		
			Collection<GrantedAuthority> roles = jwtSrv.obterAuthorities(token);
			
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
			
			return User.builder()
					.username(usuario)
					.password(usuario+"123")
					.authorities(grantedAuthorities)
					.build();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new UsernameNotFoundException(e.getMessage());
		}
	}
}
