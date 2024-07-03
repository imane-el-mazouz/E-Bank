package com.bank.repository;

import com.bank.model.Account;
import com.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
