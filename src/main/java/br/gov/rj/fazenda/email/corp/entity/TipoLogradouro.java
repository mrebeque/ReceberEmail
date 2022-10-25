package br.gov.rj.fazenda.email.corp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The persistent class for the TIPO_LOGRADOURO database table.
 */
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "TIPO_LOGRADOURO")
@NamedQuery(name = "TipoLogradouro.findAll", query = "SELECT t FROM TipoLogradouro t")
public class TipoLogradouro {

	@Id
	@Column(name = "CO_TP_LOGRADOURO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NO_ABREV_TP_LOGRADOURO")
	private String abreviacao;

	@Column(name = "NO_TP_LOGRADOURO")
	private String descricao; // DESCRICAO

}
