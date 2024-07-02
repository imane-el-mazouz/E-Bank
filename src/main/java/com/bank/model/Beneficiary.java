package com.bank.model;

import com.bank.enums.Bank;
import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idB;
    private String name;
    private Long rib;
    @Enumerated(EnumType.STRING)
    @Column(name = "bank", nullable = false, length = 225)
    private Bank bank;


}
