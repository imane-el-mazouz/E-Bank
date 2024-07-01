package com.bank.repository;

import com.bank.model.Glycemie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface GlycemieRepository extends JpaRepository<Glycemie, Long> {
    @Query(value = "SELECT * FROM glucose_reading ORDER BY YEAR(date_and_time), WEEK(date_and_time), date_and_time", nativeQuery = true)
    static List<Glycemie> findAllGroupedByWeek() {
        return null;
    }

    @Query(value = "SELECT * FROM glucose_reading ORDER BY YEAR(date_and_time), MONTH(date_and_time), date_and_time", nativeQuery = true)
    static List<Glycemie> findAllGroupedByMonth() {
        return null;
    }

    @Query(value = "SELECT * FROM glucose_reading ORDER BY YEAR(date_and_time), date_and_time", nativeQuery = true)
    static List<Glycemie> findAllGroupedByYear() {
        return null;
    }

    @Query(value = "SELECT * FROM glucose_reading WHERE YEAR(date_and_time) = ?1 AND WEEK(date_and_time) = ?2 ORDER BY date_and_time", nativeQuery = true)
    static List<Glycemie> findByYearAndWeek(int year, int week) {
        return null;
    }

    @Query(value = "SELECT * FROM glucose_reading WHERE YEAR(date_and_time) = ?1 AND MONTH(date_and_time) = ?2 ORDER BY date_and_time", nativeQuery = true)
    static List<Glycemie> findByYearAndMonth(int year, int month) {
        return null;
    }

    @Query(value = "SELECT * FROM glucose_reading ORDER BY date_and_time DESC LIMIT 1", nativeQuery = true)
    Glycemie findFirstByOrderByDateAndTimeDesc();

    @Query("SELECT g FROM Glycemie g WHERE g.date >= :startDate AND g.date < :endDate")
    List<Glycemie> findHourlyGlycemiaData(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Glycemie> findAllByDate(LocalDateTime date);

    Glycemie findTopByOrderByDateDesc();

    List<Glycemie> findByDiabetic_Id(Long diabeticId);

}


