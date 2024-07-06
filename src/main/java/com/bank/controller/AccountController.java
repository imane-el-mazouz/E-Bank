package com.bank.controller;

import com.bank.exception.AccountNotFoundException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        public ResponseEntity<List<Account>> getAllAccounts(){
            List<Account> accounts =accountService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        Account account = accountService.getAccountById(Long.valueOf(id));
        if (account != null){
            return ResponseEntity.ok(account);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Account saveAccount(@RequestBody Account account){
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
    public ResponseEntity<Void> updateAccount(@PathVariable Long id, @RequestBody Account account){
        if (accountService.getAccountById(id) != null){
            accountService.updateAccount(id, account);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/update/{id}")
//    public Account updateAccount(@RequestBody Account account) {
//        return accountService.saveAccount(account);
//    }
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
//                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getSold();
    }

    public List<Transaction> getAccountTransactions(Long id) {
        Account account = accountService.getAccountById(id);
//                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getTransactions();
    }
//    @DeleteMapping("remove/{id}")
//    public ResponseEntity<?> remove(@PathVariable String id) {
//        try{
//            accountService.getAccountById(Long.valueOf(id));
//            return ResponseEntity.noContent().build();
//        }
//        catch (EmptyResultDataAccessException e){
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAccount(@PathVariable Long id, @RequestParam String closureReason) {
//        Account account = accountService.getAccountById(id);
//        if (account != null) {
//            account.setCloseureReason(closureReason); // Update the closure reason
//            accountService.updateAccount(account); // Save the updated account
//            accountService.deleteAccount(id); // Then delete the account
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
}

