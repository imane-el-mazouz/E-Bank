package com.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Repas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double carbohydrates;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "diabetic_id")
    private Diabetic diabetic;

    @ManyToOne
    @JoinColumn(name = "glycemie_id")
    private Glycemie glycemie;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;
    // Getters and setters

    public Glycemie getGlycemie() {
        return glycemie;
    }

    public void setGlycemie(Glycemie glycemie) {
        this.glycemie = glycemie;
    }
}
