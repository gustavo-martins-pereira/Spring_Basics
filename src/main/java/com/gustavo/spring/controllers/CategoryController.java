package com.gustavo.spring.controllers;

import com.gustavo.spring.domain.Category;
import com.gustavo.spring.useCases.category.GetAllCategoriesUsecase;
import com.gustavo.spring.useCases.category.GetCategoryByIdUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private GetAllCategoriesUsecase getAllCategoriesUsecase;

    @Autowired
    private GetCategoryByIdUsecase getCategoryByIdUsecase;

    @GetMapping
    public List<Category> getAllCategory() { return getAllCategoriesUsecase.execute(); }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
        var categoryOptional = getCategoryByIdUsecase.execute(id);

        return categoryOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
