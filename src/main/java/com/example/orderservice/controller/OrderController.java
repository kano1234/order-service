package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.model.Parameter;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PutMapping("/{storeId}")
    public ResponseEntity<Order> putOrder(
            @PathVariable("storeId") long storeId,
            @RequestBody OrderRequest request) {
        Order order = Order.builder()
                .name(request.getName())
                .date(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
        simpMessagingTemplate.convertAndSend("/topic/" + storeId, order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}

