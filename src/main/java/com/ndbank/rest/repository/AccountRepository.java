package com.ndbank.rest.repository;

import java.util.List;

import com.ndbank.rest.model.Account;


public interface AccountRepository {
	
	List<Account> list();
	// CRUD
	Account create(Account account);
	Account get(Long id);
	Account update(Long id, Account account);
	Account delete(Long id);
	
}
