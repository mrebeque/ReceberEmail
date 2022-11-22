package br.gov.rj.fazenda.email.corp.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredencialDTO {
	private String usuario;
	private String codigoAplicacao;
	private Collection<GrantedAuthority> roles;
	private String token;	
	private String status;
	private String msgStatus;
}
