package com.gustavo.spring.controllers;

import com.gustavo.spring.domain.Product;
import com.gustavo.spring.useCases.category.GetAllProductsUsecase;
import com.gustavo.spring.useCases.product.GetProductByIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private GetAllProductsUsecase getAllCategoriesUsecase;

    @Autowired
    private GetProductByIdUsecase getProductByIdUsecase;

    @GetMapping
    public List<Product> getAllProduct() { return getAllCategoriesUsecase.execute(); }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        var productOptional = getProductByIdUsecase.execute(id);

        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
