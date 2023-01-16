package br.gov.rj.fazenda.email.corp.exception;

public class Result {

	private boolean status;
	
	private StringBuilder  message;

	private Long timestamp;
	
	

	public Result() {
		super();
		this.message = new StringBuilder();
		this.timestamp = System.currentTimeMillis();
	}

	public void clean( ) {
		status  = false;
		message = new StringBuilder();
		timestamp = null;
	}
	
	public StringBuilder getMessage() {
		return message;
	}

	public void informarErro(String erro) {
		this.setStatus(false);
		this.getMessage().append(erro+"; ");
	}

	public void informarSucesso(String msg) {
		if (this.status) {
			this.getMessage().append(msg);
		}
		
	}
	
	public void setMessage(StringBuilder message) {
		this.message = message;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public boolean hasErro() {
		return (!this.status);
	}	

}