package com.gustavo.spring.repositories;

import com.gustavo.spring.domain.Order;
import com.gustavo.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
