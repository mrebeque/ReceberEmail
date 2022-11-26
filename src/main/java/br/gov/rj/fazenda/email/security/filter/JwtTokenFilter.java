package br.gov.rj.fazenda.email.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.gov.rj.fazenda.email.security.service.JwtService;
import br.gov.rj.fazenda.email.security.service.impl.SecurityUserDetailsService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JwtTokenFilter extends OncePerRequestFilter {

	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = jwtService.extrairToken(request);
			
			if (token != null && jwtService.isTokenValido(token)) {
					UserDetails usuarioAutenticado = userDetailsService.loadUserByToken(token);
					
					UsernamePasswordAuthenticationToken user = 
							new UsernamePasswordAuthenticationToken(
									usuarioAutenticado, null, usuarioAutenticado.getAuthorities());
					
					user.setDetails( new WebAuthenticationDetailsSource().buildDetails(request) );
					
					SecurityContextHolder.getContext().setAuthentication(user);					
			}
		} catch (Exception e) {
			log.error("Cannot set user authentication: {}", e);
		}
		filterChain.doFilter(request, response);		
	}
}
