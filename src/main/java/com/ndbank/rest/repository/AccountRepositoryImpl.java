package com.ndbank.rest.repository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ndbank.rest.model.Account;

@Primary
@Repository
public class AccountRepositoryImpl implements AccountRepository {

	@Autowired
	@Lazy
	private AccountJpaRepository accountJPARepository;

	@Override
	public List<Account> list() {
		System.out.println("JPA Repo: " + accountJPARepository);

		return accountJPARepository.findAll();
	}

	@Override
	public Account create(Account account) {
		return accountJPARepository.saveAndFlush(account);
	}

	@Override
	public Account get(Long id) {
		return accountJPARepository.findById(id).orElse(null);
	}

	@Override
	public Account update(Long id, Account account) {
		Account existingAccount = accountJPARepository.findById(id).orElse(null);
		BeanUtils.copyProperties(account, existingAccount);

		return accountJPARepository.saveAndFlush(existingAccount);
	}

	@Override
	public Account delete(Long id) {
		Account existingAccount = accountJPARepository.findById(id).orElse(null);
		accountJPARepository.delete(existingAccount);

		return existingAccount;
	}

}
