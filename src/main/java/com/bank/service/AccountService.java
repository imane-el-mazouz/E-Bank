package com.bank.service;

import com.bank.model.Account;
import com.bank.model.User;
import com.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

@Autowired
private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long idA) {
        return accountRepository.findById(idA).orElse(null);
    }

}