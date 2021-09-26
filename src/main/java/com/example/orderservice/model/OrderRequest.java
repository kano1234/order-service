package com.example.orderservice.model;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderRequest {
    private String name;
    private String date;
}
