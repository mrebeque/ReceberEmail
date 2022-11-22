package br.gov.rj.fazenda.email.security.service;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {

	Claims obterClaims(String token) throws ExpiredJwtException;	
	boolean isTokenValido(String token);	
	String obterLoginUsuario( String token);
	String obterAppl(String token);
	String extrairToken(HttpServletRequest request);
	Collection<GrantedAuthority> obterAuthorities(String token);
	String obterTipoToken(String token);

}
