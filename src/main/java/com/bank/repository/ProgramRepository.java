package com.bank.repository;

import com.bank.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    @Query("SELECT p FROM Program  p WHERE p.diabetic.id = :diabeticId")
    List<Program> getAllProgramsById(Long diabeticId);
    List<Program> findByDiabetic_Id(Long diabeticId);


}
