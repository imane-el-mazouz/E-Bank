package com.bank.repository;

import com.bank.model.Account;
import com.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a.sold FROM Account a WHERE a.idA = :idA")
    Double findAccountSoldById(@Param("idA") Long idA);


        Optional<Account> findByRib(Long rib);
    }

