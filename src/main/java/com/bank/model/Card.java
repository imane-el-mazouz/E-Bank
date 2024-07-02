package com.bank.model;

import com.bank.enums.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeCard", nullable = false, length = 225)
    private TypeC typeCard;



    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 225)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "blockingReason", nullable = false, length = 225)
    private Reason blockingReason;
}
