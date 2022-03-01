package com.ndbank.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndbank.rest.model.Account;

public interface AccountJpaRepository extends JpaRepository<Account, Long>{


}
