package com.gustavo.spring.useCases.product;

import com.gustavo.spring.domain.Product;
import com.gustavo.spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsUsecase {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> execute() { return productRepository.findAll(); }

}
