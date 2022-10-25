package br.gov.rj.fazenda.email.corp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TipoFornecedor {
	
	@Id
	private Long id;
	private String nome;
	private Situacao situacao;
}
