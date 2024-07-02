package com.bank.model;
import com.bank.enums.Bank;
import com.bank.enums.TypeA;
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

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idA;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeA", nullable = false, length = 225)
    private TypeA typeA;

    private Double sold;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private String closeureReason ;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank", nullable = false, length = 225)
    private Bank bank;

}
