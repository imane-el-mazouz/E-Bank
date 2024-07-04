package com.bank.repository;

import com.bank.model.Beneficiary;
import com.bank.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

}
