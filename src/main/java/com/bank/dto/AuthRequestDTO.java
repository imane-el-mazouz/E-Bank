package com.bank.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDTO {
    private String name;
    private String password;
}
