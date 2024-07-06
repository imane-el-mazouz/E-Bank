package com.bank.service;

import com.bank.exception.AccountNotFoundException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.BeneficiaryRepository;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    public Double getAccountSold(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("To Account not found"));
        return account.getSold();
    }

    public List<Transaction> getAccountTransactions(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("To Account not found"));
        return account.getTransactions();
    }
    public String transferMoney(Long fromAccountId, Long toAccountId, Double amount, String description) {
        Optional<Account> fromAccountOpt = accountRepository.findById(fromAccountId);
        Optional<Account> toAccountOpt = accountRepository.findById(toAccountId);
        Account fromAccount = fromAccountOpt.orElseThrow(() -> new AccountNotFoundException("From Account not found"));
        Account toAccount = toAccountOpt.orElseThrow(() -> new AccountNotFoundException("To Account not found"));

        if (fromAccount.getSold() < amount) {
            throw new IllegalStateException("Insufficient funds in the source account");
        }

        fromAccount.setSold(fromAccount.getSold() - amount);
        toAccount.setSold(toAccount.getSold() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transactionRepository.save(transaction);
        return "Money transferred successfully";
    }


}