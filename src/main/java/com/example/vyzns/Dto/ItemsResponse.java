package com.example.vyzns.Dto;

import com.example.vyzns.Enum.Condition;
import com.example.vyzns.Enum.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ItemsResponse {

    private Long id;
    private String title;
    private String description;
    private String image_url;
    private String category;
    private BigDecimal price;
    private String status;
    private Long owner;
    private String condition;

}
