package com.bank.repository;

import com.bank.model.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
    List<Exercice> findByDiabetic_Id(Long diabeticId);
}
