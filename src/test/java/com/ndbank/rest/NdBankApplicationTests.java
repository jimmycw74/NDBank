package com.ndbank.rest;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ndbank.rest.model.Account;
import com.ndbank.rest.repository.AccountRepository;
import com.ndbank.rest.support.Exchange;

@SpringBootTest
class NdBankApplicationTests {
	
	@Autowired
	private AccountRepository accountRepository;

	
	@PostConstruct
	private void loadAccounts() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("accounts.xml");
		Map<String, Account> beans = context.getBeansOfType(Account.class);
		for (Account act : new ArrayList<>(beans.values())) {
			accountRepository.create(act);
		}
		context.close();
	}
	
	@Test
	void testShouldFailAccountNotExist() {
		Assert.assertTrue(accountRepository.get(200l)==null);
	}
	
	@Test
	void testShouldFailExchangeNotRetrieved() {
		Exchange ex = new Exchange();
		String sExchange = ex.getCurrency("XXX");
		Assert.assertTrue(sExchange.contains("DOCTYPE html"));
	}
	
	@Test
	void testShouldFailBalanceInsufficient() {
		Account act = accountRepository.get(1l);
		Assert.assertTrue(act.getBalance()<10000);
	}
	

}
