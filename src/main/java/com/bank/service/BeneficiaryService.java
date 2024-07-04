package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Beneficiary;
import com.bank.repository.AccountRepository;
import com.bank.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    public List<Beneficiary> getAllBeneficiaries(){
        return beneficiaryRepository.findAll();
    }

public Beneficiary getBeneficiaryById(Long id){
        return beneficiaryRepository.findById(id).orElse(null);
}


public Beneficiary saveBeneficiary(Beneficiary beneficiary){
        return beneficiaryRepository.save(beneficiary);
}

    public void deleteBeneficiary(Long id) {
        beneficiaryRepository.deleteById(id);
    }

}