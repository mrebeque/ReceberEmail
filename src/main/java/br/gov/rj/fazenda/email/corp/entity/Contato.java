package br.gov.rj.fazenda.email.corp.entity;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Contato {

	private String email;
	private String telefone;
	private String fax;
	private String celular;
}
