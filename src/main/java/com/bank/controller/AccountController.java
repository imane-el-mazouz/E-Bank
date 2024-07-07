package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        Account account = accountService.getAccountById(Long.valueOf(id));
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Account saveAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        if (accountService.getAccountById(id) != null) {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        if (accountService.getAccountById(id) != null) {
            accountService.updateAccount(id, account);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/close/{id}")
    public ResponseEntity<Void> closeAccount(@PathVariable Long id, @RequestBody String reason) {
        try {
            accountService.closeAccount(id, reason);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public Double getAccountSold(Long id) {
        Account account = accountService.getAccountById(id);
        return account.getSold();
    }

    public List<Transaction> getAccountTransactions(Long id) {
        Account account = accountService.getAccountById(id);
        return account.getTransactions();
    }
}
