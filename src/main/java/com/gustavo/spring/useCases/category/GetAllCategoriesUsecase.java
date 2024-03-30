package com.gustavo.spring.useCases.category;

import com.gustavo.spring.domain.Category;
import com.gustavo.spring.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCategoriesUsecase {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> execute() {
        return categoryRepository.findAll();
    }

}
