package com.gustavo.spring.useCases.category;

import com.gustavo.spring.domain.Category;
import com.gustavo.spring.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetCategoryByIdUsecase {

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> execute(UUID id) {
        return categoryRepository.findById(id);
    }

}
