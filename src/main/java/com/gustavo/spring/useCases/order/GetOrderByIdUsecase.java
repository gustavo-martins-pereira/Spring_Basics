package com.gustavo.spring.useCases.order;

import com.gustavo.spring.domain.Order;
import com.gustavo.spring.domain.User;
import com.gustavo.spring.repositories.OrderRepository;
import com.gustavo.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetOrderByIdUsecase {

    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> execute(UUID id) {
        return orderRepository.findById(id);
    }

}
