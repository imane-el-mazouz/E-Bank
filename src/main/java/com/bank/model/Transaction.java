package com.bank.model;

import com.bank.enums.Reason;
import com.bank.enums.Status;
import com.bank.enums.TypeC;
import com.bank.enums.TypeTransaction;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idT;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private Double amount ;
    private Long ribT ;


    @Enumerated(EnumType.STRING)
    @Column(name = "typeT", nullable = false, length = 225)
    private TypeTransaction typeT;

    private String description ;

    @ManyToOne
    @JoinColumn(name = "beneficiary_id")
    private Beneficiary beneficiary;
}
