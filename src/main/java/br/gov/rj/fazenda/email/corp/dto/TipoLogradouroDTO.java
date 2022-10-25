package br.gov.rj.fazenda.email.corp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString @EqualsAndHashCode
public class TipoLogradouroDTO {
	
	private Long id;
	private String abreviacao;
	private String descricao;

}
