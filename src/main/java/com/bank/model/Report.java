package com.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diabetic_id")
    private Diabetic diabetic;

    @OneToMany(mappedBy = "report")
    private List<Glycemie> glycemiaReadings;

    @OneToMany(mappedBy = "report")
    private List<Repas> meals;

    @OneToMany(mappedBy = "report")
    private List<Program> programs;
}
