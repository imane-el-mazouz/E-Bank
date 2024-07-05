package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.CardRepository;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BeneficiaryService beneficiaryService;


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

//    @Autowired
//    private DiabeticRepository diabeticRepository;
//    public Diabetic update(Diabetic diabetic) {
//        return diabeticRepository.save(diabetic);
//    }
//
//    public List<Diabetic> getAll() {
//        return diabeticRepository.findAll();
//    }
//
//    public Diabetic save(Diabetic diabetic) {
//        return diabeticRepository.save(diabetic);
//    }
//
//    public Diabetic findDiabetic(String email, String password){return diabeticRepository.findDiabrtic(email, password);}
//
//    public Diabetic getById(Long id) {
//        return diabeticRepository.findById(id).orElseThrow(DiabeticNotFoundException::new);
//    }
//
//    public void delete(Long id) {
//        diabeticRepository.findById(id).orElseThrow(DiabeticNotFoundException::new);
//        diabeticRepository.deleteById(id);
//    }
//
//    public Diabetic getDiabeticById(Long id) {
//        return diabeticRepository.findById(id).orElse(null);
//    }

}