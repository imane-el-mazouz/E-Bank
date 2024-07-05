package com.bank.repository;

import com.bank.model.Card;
import com.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
