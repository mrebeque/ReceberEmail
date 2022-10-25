package br.gov.rj.fazenda.email.corp.entity;

import lombok.Getter;

@Getter
public enum Situacao {
	
	ATIVO("A", "Ativo"), INATIVO("I", "Inativo");
	
	private String codigo;
	private String descricao;
	
	private Situacao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
}
