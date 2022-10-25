package br.gov.rj.fazenda.email.corp.exception;

public class SefazException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer errorCode;

	public String errorMessage;
	
	public SefazException(Integer errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
