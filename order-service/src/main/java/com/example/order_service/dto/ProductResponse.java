package com.example.order_service.dto;


import jakarta.persistence.NamedQueries;
import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private String category;
}
