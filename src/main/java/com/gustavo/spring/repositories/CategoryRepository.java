package com.gustavo.spring.repositories;

import com.gustavo.spring.domain.Category;
import com.gustavo.spring.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
