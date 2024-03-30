package com.gustavo.spring.controllers;

import com.gustavo.spring.domain.Order;
import com.gustavo.spring.useCases.order.GetAllOrdersUsecase;
import com.gustavo.spring.useCases.order.GetOrderByIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private GetAllOrdersUsecase getAllOrdersUsecase;

    @Autowired
    private GetOrderByIdUsecase getOrderByIdUsecase;

    @GetMapping
    public List<Order> getAllOrder() {
        return getAllOrdersUsecase.execute();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id) {
        var orderOptional = getOrderByIdUsecase.execute(id);

        return orderOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
