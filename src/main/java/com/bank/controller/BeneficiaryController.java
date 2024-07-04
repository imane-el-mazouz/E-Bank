package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.Beneficiary;
import com.bank.service.AccountService;
import com.bank.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benef")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

        @GetMapping
        public ResponseEntity<List<Beneficiary>> getAllBeneficiaries() {
            List<Beneficiary> beneficiaries = beneficiaryService.getAllBeneficiaries();
            return ResponseEntity.ok(beneficiaries);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Beneficiary> getBeneficiaryById(@PathVariable String id) {
            Beneficiary beneficiary = beneficiaryService.getBeneficiaryById(Long.valueOf(id));
            if (beneficiary != null) {
                return ResponseEntity.ok(beneficiary);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public Beneficiary saveBeneficiary(@RequestBody Beneficiary beneficiary) {
            return beneficiaryService.saveBeneficiary(beneficiary);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBeneficiary(@PathVariable Long id) {
            if (beneficiaryService.getBeneficiaryById(id) != null) {
                beneficiaryService.deleteBeneficiary(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

//        @PutMapping("/update/{id}")
//        public ResponseEntity<Void> updateAccount(@PathVariable Long id, @RequestBody Account account) {
//            if (accountService.getAccountById(id) != null) {
//                accountService.updateAccount(id, account);
//                return ResponseEntity.noContent().build();
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        }
//
//        //    @PutMapping("/update/{id}")
////    public Account updateAccount(@RequestBody Account account) {
////        return accountService.saveAccount(account);
////    }
//        @PutMapping("/close/{id}")
//        public ResponseEntity<Void> closeAccount(@PathVariable Long id, @RequestParam String reason) {
//            try {
//                accountService.closeAccount(id, reason);
//                return ResponseEntity.noContent().build();
//            } catch (IllegalArgumentException | IllegalStateException e) {
//                return ResponseEntity.badRequest().build();
//            }
//        }
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
