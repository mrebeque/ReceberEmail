package br.gov.rj.fazenda.email.corp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "FORNECEDOR")
public class Fornecedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeFantasia;
	private String razaoSocial;
	private String cpf;
	private String cnpj;
	private String inscricaoMunicipal;
	private String inscricaoEstadual;
	private String rg;
	private String email;
	private String telefone;
	private String celular;

	@Column
	private String rua;

	@Column
	private String numero;

	@Column
	private String complemento;

	@Column
	private String bairro;

	@Column
	private String cep;
	
}
