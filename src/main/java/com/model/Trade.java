package com.model;

import javax.validation.constraints.NotBlank;

public class Trade {

	@NotBlank(message = "Trade is mandatory")
	private String tradeId;
	
	@NotBlank(message = "Version is mandatory")
	private int version;
	
	@NotBlank(message = "Counter-Party Id is mandatory")
	private String counterPartyId;
	
	@NotBlank(message = "Book Id is mandatory")
	private String bookId;
	
	@NotBlank(message = "MaturityDate Id is mandatory")
	private String maturityDate;

	private String createdDate;
	private char expired;

	public Trade() {

	}
	
	

	public Trade(String tradeId, int version, String counterPartyId, String bookId, String maturityDate,
			String createdDate, char expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}



	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public char getExpired() {
		return expired;
	}

	public void setExpired(char expired) {
		this.expired = expired;
	}
	
}

