package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

//    @PutMapping("/close/{id}")
//    public ResponseEntity<Void> closeAccount(@PathVariable Long id, @RequestBody String reason) {
//        try {
//            accountService.closeAccount(id, reason);
//            return ResponseEntity.noContent().build();
//        } catch (IllegalArgumentException | IllegalStateException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
@PutMapping("/close/{id}")
public ResponseEntity<Void> closeAccount(@PathVariable Long id, @RequestBody Map<String, String> request) {
    String reason = request.get("reason");
    if (reason == null || reason.isEmpty()) {
        return ResponseEntity.badRequest().build();
    }

    try {
        accountService.closeAccount(id, reason);
        return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
//
//    public Double getAccountSold(Long id) {
//        Account account = accountService.getAccountById(id);
//        return account.getSold();
//    }
//
//    public List<Transaction> getAccountTransactions(Long id) {
//        Account account = accountService.getAccountById(id);
//        return account.getTransactions();
//    }


    @GetMapping("/{id}/sold")
    public ResponseEntity<Double> getAccountBalance(@PathVariable Long id) {
        Double balance = accountService.getAccountBalance(id);
        return ResponseEntity.ok(balance);
    }


    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable Long id) {
        List<Transaction> transactions = accountService.getAccountTransactions(id);
        return ResponseEntity.ok(transactions);
    }
}
