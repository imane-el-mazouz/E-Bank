package com.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long idU;
    private String name;
    private String profession;
    private String email;
    private String password;
    private String phone;

    public UserDto(Long idU, String email, String name, String password, String profession ,String phone) {
        this.idU = idU;
        this.email = email;
        this.name = name;
        this.password=password;
        this.profession= profession;
        this.phone = phone;
    }
}
