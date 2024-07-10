package com.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
//    private String username;
    private String password;
    private String profession;
    private String phone;



    public UserDto(Long idU, String email, String name, String password, String profession, String phone) {
    }

    public UserDto(Long idU, String email, String name, String password, String profession) {
    }
}
