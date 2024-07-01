package com.bank.repository;

import com.bank.model.Repas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepasRepository extends JpaRepository<Repas, Long> {
    List<Repas> findByDiabetic_Id(Long diabeticId);

}

