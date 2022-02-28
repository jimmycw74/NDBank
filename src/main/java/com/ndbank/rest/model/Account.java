package com.ndbank.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String currency;
	private float balance;
	
	public Account() {}
	
	public Account(Long id, String currency, float balance) {
		this.id = id;
		this.currency = currency;
		this.balance = balance;
	}

	public Long getOwnerId() {
		return id;
	}

	public void setOwnerId(Long ownerId) {
		id = ownerId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [OwnerId=" + id + ", Currency=" + currency + ", Balance=" + balance + "]";
	}
	
}
