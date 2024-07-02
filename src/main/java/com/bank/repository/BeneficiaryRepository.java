package com.bank.repository;

import com.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<User, Long> {
//    @Query("SELECT d FROM Diabetic d WHERE d.email = :email and d.password= :password")
//    Diabetic findDiabrtic(String email, String password);
}
