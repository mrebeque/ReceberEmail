package br.gov.rj.fazenda.email.corp.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
	private String Status;
	private String mensagem;
	private String retorno;
	
}
