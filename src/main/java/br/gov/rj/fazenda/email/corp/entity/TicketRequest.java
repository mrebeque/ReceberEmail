package br.gov.rj.fazenda.email.corp.entity;

import java.io.Serializable;

public class TicketRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String ticket;
	
	//need default constructor for JSON Parsing
	public TicketRequest()
	{
		
	}

	public TicketRequest(String ticket) {
		this.setTicket(ticket);
	}

	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}