package com.bank.repository;

import com.bank.model.Conseil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Conseil, Long> {

}
