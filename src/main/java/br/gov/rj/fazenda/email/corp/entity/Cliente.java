package br.gov.rj.fazenda.email.corp.entity;

import java.time.LocalDate;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	private String numeroCNH;
	private String numeroRegistroCNH;
	private LocalDate validadeCNH;
	private String senha;
	private String observacao;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_cidade")
//	private Cidade cidade;
//	@Enumerated(EnumType.STRING)
//	private Situacao situacao;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_cidade")
//	private Cidade cidade;
//	@Enumerated(EnumType.STRING)
//	private Situacao situacao;

}
