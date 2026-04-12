package com.example.vyzns.Dto;


import lombok.Data;

@Data
public class ExchangeRequest {

    private Long fromUserId;
    private Long toUserId;
    private Long offeredItemId;
    private Long requestedItemId;
     private  String status;
    private String message;
}
