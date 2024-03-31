package com.gustavo.spring.useCases.product;

import com.gustavo.spring.domain.Product;
import com.gustavo.spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetProductByIdUsecase {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> execute(UUID id) {
        return productRepository.findById(id);
    }

}
