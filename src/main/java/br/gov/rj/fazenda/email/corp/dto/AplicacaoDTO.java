package br.gov.rj.fazenda.email.corp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AplicacaoDTO {

	private String codigo;
	private String nome;
	
	@JsonIgnore
	private String chaveJwt;

	@JsonIgnore
	private Long expiracaoToken;
	
	@JsonIgnore
	private String url;
}
