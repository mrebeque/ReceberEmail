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
public class FornecedorSaidaDTO {
	private Long id;
	private String nomeFantasia;
	private String razaoSocial;
	private String cpf;
	private String cnpj;
	private String inscricaoMunicipal;
	private String inscricaoEstadual;
	private String rg;

}
