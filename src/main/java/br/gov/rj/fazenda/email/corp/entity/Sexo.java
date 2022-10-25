package br.gov.rj.fazenda.email.corp.entity;

public enum Sexo {

	MASCULINO("M"), FEMININO("F");

	private String codigo;
	
	private Sexo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
