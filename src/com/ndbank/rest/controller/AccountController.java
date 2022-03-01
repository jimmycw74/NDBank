package com.ndbank.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ndbank.rest.model.Account;
import com.ndbank.rest.model.Transfer;
import com.ndbank.rest.repository.AccountRepository;
import com.ndbank.rest.support.Exchange;

@RestController
@RequestMapping("api/v1/")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping(value = "accounts", method = RequestMethod.GET)
	public List<Account> list() {
		return accountRepository.list();
	}

	// crud
	@RequestMapping(value = "accounts", method = RequestMethod.POST)
	public Account create(@RequestBody Account account) {
		return accountRepository.create(account);
	}

	@RequestMapping(value = "accounts/{id}", method = RequestMethod.GET)
	public Account get(@PathVariable Long id) {
		return accountRepository.get(id);
	}

	@RequestMapping(value = "accounts/{id}", method = RequestMethod.PUT)
	public Account update(@PathVariable Long id, @RequestBody Account shipwreck) {
		Account act = accountRepository.get(id);
		shipwreck.setOwnerId(id);
		shipwreck.setBalance(act.getBalance());
		return accountRepository.update(id, shipwreck);
	}

	@RequestMapping(value = "accounts/{id}", method = RequestMethod.DELETE)
	public Account delete(@PathVariable Long id) {
		return accountRepository.delete(id);
	}

	@RequestMapping(value = "accounts/transfer", method = RequestMethod.POST)
	public ResponseEntity transfer(@RequestBody Transfer order) {

		String returnValue = "";

		try {

			// check if both account exists
			Account act1 = accountRepository.get(order.getIdFrom());
			Account act2 = accountRepository.get(order.getIdTo());
			if (act1 == null || act2 == null)
				return new ResponseEntity(HttpStatus.NOT_FOUND);

			// check balance
			if (act1.getBalance() < order.getAmount())
				return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);

			// check currency
			Float exchange;
			if (!act1.getCurrency().equals(act2.getCurrency())) {
				Exchange ex = new Exchange();
				JsonObject json = JsonParser.parseString(ex.getCurrency(act1.getCurrency())).getAsJsonObject();
				if (json == null)
					return new ResponseEntity(HttpStatus.NOT_FOUND);
				exchange = json.get("data").getAsJsonObject().get(act2.getCurrency()).getAsFloat();
				if (exchange == null)
					return new ResponseEntity(HttpStatus.NOT_FOUND);
			} else
				exchange = 1f;

			// transfer money
			float convertedAmount = order.getAmount() * exchange;
			act1.setBalance(act1.getBalance() - order.getAmount());
			act2.setBalance(act2.getBalance() + convertedAmount);

			accountRepository.update(act1.getOwnerId(), act1);
			accountRepository.update(act2.getOwnerId(), act2);

		} catch (Exception ex) {
			return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

}
