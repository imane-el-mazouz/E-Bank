package com.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime; // Import ajouté

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString(exclude = {"diabetic", "exercice", "glycemie"})
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diabetic_id")
    private Diabetic diabetic;

    private String name;
    private Time duration;
    private Float bloodSugarBefore;
    private Float bloodSugarAfter;

    private LocalDateTime date; 

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;


    public void setExercice(Exercice exercice) {
    }

    public void setGlycemie(Glycemie glycemie) {
    }
}



