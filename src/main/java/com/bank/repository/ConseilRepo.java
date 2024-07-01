package com.bank.repository;

import com.bank.enums.Level;
import com.bank.model.Conseil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConseilRepo extends JpaRepository<Conseil, Long> {
    Optional<Conseil> findByLevel(Level level);
}
