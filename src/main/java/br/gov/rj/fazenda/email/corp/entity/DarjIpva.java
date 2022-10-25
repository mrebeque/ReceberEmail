package br.gov.rj.fazenda.email.corp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class DarjIpva implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3166024303526434488L;
	
	private int id;
	
	// MUITOS DESTES CAMPOS NÃO ESTÃO SENDO UTILZADOS NO BOLETO, SOMENTE TER OS QUE SERÃO UTILIZADOS
	private String cpNoRazao;
	private String pcNoRazao;
	private String via;
	private String qtDebitos;
	private BigDecimal qtCads;
	private String dsGrupo;
	private BigDecimal qtNaturezas;
	private Timestamp dtVencimento;
	private String nuNossoNumero;
	private BigDecimal sqDocArrecadacao;
	private BigDecimal vlPrincipal;
	private BigDecimal vlMora;
	private BigDecimal vlMultaMora;
	private BigDecimal vlMulta;
	private BigDecimal vlTotal;
	private String dsLinhaDigitavel;
	private String cdBarras;
	private String dsNatureza;
	private String noRazao;
	private String cpfCnpj;
	private String dsEndereco;
	private String noMunicipio;
	private String sgUf;
	private String nuCep;
	private String descricao;
	private String cpCpfCnpj;
	private String cpDsEndereco;
	private String cpNoMunicipio;
	private String cpSgUf;
	private String cpNuCep;
	private String pcDsEndereco;
	private String pcPcfCnpj;
	private String pcNoMunicipio;
	private String pcSgUf;
	private String pcNuCep;
	private String qrCode;
	
	// CAMPOS QUE ERAM PARAMETROS E IRÃO VIRAR FIELDS
	
	private String bBradescoChequeEspecialDesc;
	
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getQtDebitos() {
		return qtDebitos;
	}
	public void setQtDebitos(String qtDebitos) {
		this.qtDebitos = qtDebitos;
	}
	public BigDecimal getQtCads() {
		return qtCads;
	}
	public void setQtCads(BigDecimal qtCads) {
		this.qtCads = qtCads;
	}
	public Timestamp getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(Timestamp dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	public String getNuNossoNumero() {
		return nuNossoNumero;
	}
	public void setNuNossoNumero(String nuNossoNumero) {
		this.nuNossoNumero = nuNossoNumero;
	}
	public BigDecimal getSqDocArrecadacao() {
		return sqDocArrecadacao;
	}
	public void setSqDocArrecadacao(BigDecimal sqDocArrecadacao) {
		this.sqDocArrecadacao = sqDocArrecadacao;
	}
	public BigDecimal getVlPrincipal() {
		return vlPrincipal;
	}
	public void setVlPrincipal(BigDecimal vlPrincipal) {
		this.vlPrincipal = vlPrincipal;
	}
	public BigDecimal getVlMora() {
		return vlMora;
	}
	public void setVlMora(BigDecimal vlMora) {
		this.vlMora = vlMora;
	}
	public BigDecimal getVlMultaMora() {
		return vlMultaMora;
	}
	public void setVlMultaMora(BigDecimal vlMultaMora) {
		this.vlMultaMora = vlMultaMora;
	}
	public BigDecimal getVlMulta() {
		return vlMulta;
	}
	public void setVlMulta(BigDecimal vlMulta) {
		this.vlMulta = vlMulta;
	}
	public BigDecimal getVlTotal() {
		return vlTotal;
	}
	public void setVlTotal(BigDecimal vlTotal) {
		this.vlTotal = vlTotal;
	}
	public String getDsLinhaDigitavel() {
		return dsLinhaDigitavel;
	}
	public void setDsLinhaDigitavel(String dsLinhaDigitavel) {
		this.dsLinhaDigitavel = dsLinhaDigitavel;
	}
	public String getCdBarras() {
		return cdBarras;
	}
	public void setCdBarras(String cdBarras) {
		this.cdBarras = cdBarras;
	}
	public String getDsNatureza() {
		return dsNatureza;
	}
	public void setDsNatureza(String dsNatureza) {
		this.dsNatureza = dsNatureza;
	}
	public String getNoRazao() {
		return noRazao;
	}
	public void setNoRazao(String noRazao) {
		this.noRazao = noRazao;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getDsEndereco() {
		return dsEndereco;
	}
	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}
	public String getNoMunicipio() {
		return noMunicipio;
	}
	public void setNoMunicipio(String noMunicipio) {
		this.noMunicipio = noMunicipio;
	}
	public String getSgUf() {
		return sgUf;
	}
	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}
	public String getNuCep() {
		return nuCep;
	}
	public void setNuCep(String nuCep) {
		this.nuCep = nuCep;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCpCpfCnpj() {
		return cpCpfCnpj;
	}
	public void setCpCpfCnpj(String cpCpfCnpj) {
		this.cpCpfCnpj = cpCpfCnpj;
	}
	public String getCpDsEndereco() {
		return cpDsEndereco;
	}
	public void setCpDsEndereco(String cpDsEndereco) {
		this.cpDsEndereco = cpDsEndereco;
	}
	public String getCpNoMunicipio() {
		return cpNoMunicipio;
	}
	public void setCpNoMunicipio(String cpNoMunicipio) {
		this.cpNoMunicipio = cpNoMunicipio;
	}
	public String getCpSgUf() {
		return cpSgUf;
	}
	public void setCpSgUf(String cpSgUf) {
		this.cpSgUf = cpSgUf;
	}
	public String getCpNuCep() {
		return cpNuCep;
	}
	public void setCpNuCep(String cpNuCep) {
		this.cpNuCep = cpNuCep;
	}
	public String getPcDsEndereco() {
		return pcDsEndereco;
	}
	public void setPcDsEndereco(String pcDsEndereco) {
		this.pcDsEndereco = pcDsEndereco;
	}
	public String getPcPcfCnpj() {
		return pcPcfCnpj;
	}
	public void setPcPcfCnpj(String pcPcfCnpj) {
		this.pcPcfCnpj = pcPcfCnpj;
	}
	public String getPcNoMunicipio() {
		return pcNoMunicipio;
	}
	public void setPcNoMunicipio(String pcNoMunicipio) {
		this.pcNoMunicipio = pcNoMunicipio;
	}
	public String getPcSgUf() {
		return pcSgUf;
	}
	public void setPcSgUf(String pcSgUf) {
		this.pcSgUf = pcSgUf;
	}
	public String getPcNuCep() {
		return pcNuCep;
	}
	public void setPcNuCep(String pcNuCep) {
		this.pcNuCep = pcNuCep;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getbBradescoChequeEspecialDesc() {
		return bBradescoChequeEspecialDesc;
	}
	public void setbBradescoChequeEspecialDesc(String bBradescoChequeEspecialDesc) {
		this.bBradescoChequeEspecialDesc = bBradescoChequeEspecialDesc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getQtNaturezas() {
		return qtNaturezas;
	}
	public void setQtNaturezas(BigDecimal qtNaturezas) {
		this.qtNaturezas = qtNaturezas;
	}
	public String getDsGrupo() {
		return dsGrupo;
	}
	public void setDsGrupo(String dsGrupo) {
		this.dsGrupo = dsGrupo;
	}
	public String getPcNoRazao() {
		return pcNoRazao;
	}
	public void setPcNoRazao(String pcNoRazao) {
		this.pcNoRazao = pcNoRazao;
	}
	public String getCpNoRazao() {
		return cpNoRazao;
	}
	public void setCpNoRazao(String cpNoRazao) {
		this.cpNoRazao = cpNoRazao;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	
	

}
