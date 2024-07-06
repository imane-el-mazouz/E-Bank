package com.bank.controller;

import com.bank.model.Transaction;
import com.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

@Autowired
    private TransactionService transactionService;
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions =transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
//    @PostMapping("/transfer")
//    public String transferMoney(@RequestBody Transaction request) {
//        return transactionService.transferMoney(request);
//    }

    @PostMapping("/transfer")
    public String transferMoney(@RequestParam Long fromAccountId,
                                @RequestParam Long toAccountId,
                                @RequestParam Double amount,
                                @RequestParam String description) {
        return transactionService.transferMoney(fromAccountId, toAccountId, amount, description);
    }
}