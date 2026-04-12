package com.example.vyzns.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Double rating;
    private Boolean isVerified;
}