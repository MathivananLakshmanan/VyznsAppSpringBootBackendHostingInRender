package com.example.vyzns.Dto;


import com.example.vyzns.Enum.Condition;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequest {

    private String title;
    private   String  description;
    private String  image_url;
    private String  category;
    private BigDecimal price;
    private Condition condition;

}
