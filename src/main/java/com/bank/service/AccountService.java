package com.bank.service;

import com.bank.enums.Reason;
import com.bank.enums.Status;
import com.bank.enums.TypeC;
import com.bank.exception.AccountNotFoundException;
import com.bank.model.Account;
import com.bank.model.Card;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

//    public List<Account> getAccountsByUserId(Long idU) {
//        return accountRepository.findByIdU(idU);
//    }

    public List<Account> getAccountsByUserId(Long idU) {
        return accountRepository.findByUserId(idU);
    }
    @Transactional
    public Account saveAccount(Account account) {
        Account savedAccount = accountRepository.save(account);

        Card card = new Card();
        card.setExpirationDate(LocalDateTime.now().plusYears(2));
        card.setTypeCard(TypeC.debit);
        card.setStatus(Status.activated);
        card.setBlockingReason(Reason.none);
        card.setAccount(savedAccount);

        cardRepository.save(card);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        savedAccount.setCards(cards);

        return accountRepository.save(savedAccount);
    }

    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        cardRepository.deleteCardByAccountIdA(id);
        accountRepository.deleteById(id);
    }

    @Transactional
    public Account updateAccount(Long id, Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        existingAccount.setTypeA(updatedAccount.getTypeA());
        existingAccount.setSold(updatedAccount.getSold());
        existingAccount.setDate(updatedAccount.getDate());
        existingAccount.setAccountClosed(updatedAccount.getAccountClosed());
        existingAccount.setCloseureReason(updatedAccount.getCloseureReason());
        existingAccount.setBank(updatedAccount.getBank());
        existingAccount.setUser(updatedAccount.getUser());
        existingAccount.setBeneficiaries(updatedAccount.getBeneficiaries());
        existingAccount.setCards(updatedAccount.getCards());

        return accountRepository.save(existingAccount);
    }

    @Transactional
    public void closeAccount(Long id, String reason) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (account.getSold() != 0) {
            throw new IllegalStateException("Account balance must be zero to close the account");
        }

        account.setAccountClosed(true);
        account.setCloseureReason(reason);
        accountRepository.save(account);
    }

    public Double getAccountBalance(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getSold();
    }

    public List<Transaction> getAccountTransactions(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getTransactions();
    }
}
