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

        @PutMapping("/edit/{id}")
        public ResponseEntity<Void> updateBeneficiary(@PathVariable Long id, @RequestBody Beneficiary beneficiary) {
            if (beneficiaryService.getBeneficiaryById(id) != null) {
                beneficiaryService.updateBeneficiary(id, beneficiary);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}
