package br.gov.rj.fazenda.email.corp.dto;

import java.time.LocalDate;

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
@ToString 
@EqualsAndHashCode
public class ClienteSaidaDTO {
	
	private Long id;
	private String nome;
	private String razaoSocial;
	private String cpf;
	private String cnpj;
	private String rg;
	private LocalDate dataNascimento;
	private String email;
	private String telefone;
	private String celular;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String numeroCNH;
	private String numeroRegistroCNH;
	private LocalDate validadeCNH;
	private String senha;
	private String observacao;
}
