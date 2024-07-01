package com.bank.model;
import com.bank.enums.Level;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity

public class Conseil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Level level;
    private String message;


    public Conseil(Level level, String message) {
        this.level = level;
        this.message = message;
    }
}
