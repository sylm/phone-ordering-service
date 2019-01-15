package com.bbiloskursky.controller;

import com.bbiloskursky.model.Order;
import com.bbiloskursky.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-service/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        orderService.validateOrder(order);
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

}
