package com.gustavo.spring.repositories;

import com.gustavo.spring.domain.relationship.OrderItemRelationship;
import com.gustavo.spring.domain.relationship.pks.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRelationshipRepository extends JpaRepository<OrderItemRelationship, OrderItemPK> {
}
