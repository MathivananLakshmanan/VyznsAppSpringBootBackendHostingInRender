package com.example.vyzns.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private  String name;
    private String email;
    private String  password;

}
