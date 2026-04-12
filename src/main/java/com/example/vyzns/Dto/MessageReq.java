package com.example.vyzns.Dto;


import lombok.Data;

@Data
public class MessageReq {

    private Long exchangeId;
    private Long senderId;
    private boolean read;
    private String message;
}
