package com.ndbank.rest.model;

public class Transfer {
	
	private Long IdFrom;
	private Long IdTo;
	private float Amount;
	
	public Transfer(Long idFrom, Long idTo, float Amount) {
		super();
		this.IdFrom = idFrom;
		this.IdTo = idTo;
		this.Amount = Amount;
	}
	
	@Override
	public String toString() {
		return "Transfer [IdFrom=" + IdFrom + ", IdTo=" + IdTo + ", Amount=" + Amount + "]";
	}
	public Long getIdFrom() {
		return IdFrom;
	}
	public void setIdFrom(Long idFrom) {
		IdFrom = idFrom;
	}
	public Long getIdTo() {
		return IdTo;
	}
	public void setIdTo(Long idTo) {
		IdTo = idTo;
	}
	public float getAmount() {
		return Amount;
	}
	public void setAmount(float amount) {
		Amount = amount;
	}
	
	

}
