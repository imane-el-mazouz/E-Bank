package com.bank.service;

import com.bank.exception.AccountNotFoundException;
import com.bank.model.Account;
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

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }


    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        accountRepository.deleteById(id);
    }

    public void updateAccount(Long id, Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        existingAccount.setTypeA(updatedAccount.getTypeA());
        existingAccount.setSold(updatedAccount.getSold());
        existingAccount.setDate(updatedAccount.getDate());
        existingAccount.setAccountClosed(updatedAccount.getAccountClosed());
        existingAccount.setCloseureReason(updatedAccount.getCloseureReason());
        existingAccount.setBank(updatedAccount.getBank());
        existingAccount.setUser(updatedAccount.getUser());
        existingAccount.setBeneficiaries(updatedAccount.getBeneficiaries());
        existingAccount.setCards(updatedAccount.getCards());

        accountRepository.save(existingAccount);
    }

    public void closeAccount(Long accountId, String reason) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        if (account.getSold() != 0) {
            throw new IllegalStateException("Account sold must be zero before closing.");
        }
        account.setAccountClosed(true);
        account.setCloseureReason(reason);
        accountRepository.save(account);
    }
}
