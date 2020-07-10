package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.repositories.CategoryRespository;
import com.kowalczyk.hurtownia.model.responses.CategoryRestModel;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    private final CategoryRespository categoryRespository;

    public CategoryService(CategoryRespository categoryRespository) {
        this.categoryRespository = categoryRespository;
    }

    public Iterable<Category> getAll() {
        return categoryRespository.findAll();
    }

    public void saveCategory(CategoryRestModel category) {
        categoryRespository.save(mapRestModel(category));
    }

    private Category mapRestModel(final CategoryRestModel model) {
        return new Category(model.getNameOfCategory());
    }
}
