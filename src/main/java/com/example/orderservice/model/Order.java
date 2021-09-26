package com.example.orderservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private String name;
    private String date;
}
