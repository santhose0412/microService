package com.example.order_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double totalPrice;
 }
