package com.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idU;
    private String name;
    private String profession;
    private String email;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Account> accounts;
//
//    @OneToMany(mappedBy = "user")
//    private List<Card> cards;


    public User(Long idU, String name, String profession, String email, String password , String phone) {
        this.idU = idU;
        this.name = name;
        this.profession = profession;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }



}
