package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.User;
import com.bank.service.AccountService;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

       @Autowired
       private AccountService accountService;
        @GetMapping("/accounts")
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

}