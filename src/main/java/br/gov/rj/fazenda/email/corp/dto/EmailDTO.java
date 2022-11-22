package br.gov.rj.fazenda.email.corp.dto;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 163681670907461960L;
	

	@JsonProperty("to")
	private String to;

	@JsonProperty("from")
	private String from;
	
	@JsonProperty("corpo")
	private String corpo;

	@JsonProperty("subject")
	private String subject;
	
	@JsonProperty("sistema")
	private String sistema;
	
	@JsonProperty("tipoEmail")
	private String tipoEmail;
	
	@JsonProperty("dataEnvio")	
	private String dataEnvio;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("erro")
	private String error;
	
	@JsonProperty("copia")
	private String copia;
	
	@JsonProperty("pastaAnexos")
	private String pastaAnexos;
	
	@JsonProperty("arquivos")
	HashMap<String, String> arquivos = new HashMap<>(); 
	
	@JsonProperty("id")
	private long id;	

	public EmailDTO(String to, String corpo, String subject, HashMap<String, String> arquivos) {
		super();
		this.to = to;
		this.corpo = corpo;
		this.subject = subject;
		if (arquivos.isEmpty()) {
			this.arquivos = new HashMap<>();
		} else {
			this.arquivos = arquivos;
		}					
	}	
	
	public EmailDTO(String to, String corpo, String subject) {
		super();
		this.to = to;
		this.corpo = corpo;
		this.subject = subject;
	}
	
	public EmailDTO(String to, String from, String corpo, String subject, String sistema, String tipoEmail, 
			String dataEnvio, String status, String error, String copia, String performance, HashMap<String, String> arquivos) {
		super();
		this.to = to;
		this.from = from;
		this.corpo = corpo;
		this.subject = subject;
		this.sistema = sistema;
		this.tipoEmail = tipoEmail;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.error = error;
		this.copia = copia;
		this.arquivos = arquivos;
	}
	
	public EmailDTO() {
		super();
	}

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTipoEmail() {
		return tipoEmail;
	}
	public void setTipoEmail(String tipoEmail) {
		this.tipoEmail = tipoEmail;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCopia() {
		return copia;
	}
	public void setCopia(String copia) {
		this.copia = copia;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getPastaAnexos() {
		return pastaAnexos;
	}

	public void setPastaAnexos(String pastaAnexos) {
		this.pastaAnexos = pastaAnexos;
	}

	public HashMap<String, String> getArquivos() {
		return arquivos;
	}

	public void setArquivos(HashMap<String, String> arquivos) {
		this.arquivos = arquivos;
	}	
	
}
