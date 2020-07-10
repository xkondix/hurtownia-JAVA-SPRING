package com.kowalczyk.hurtownia.controller;

import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.responses.CategoryRestModel;
import com.kowalczyk.hurtownia.model.services.CategoryService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
public class CategoryController {



    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("category")
    public Iterable<Category> getAll()
    {
        return categoryService.getAll();
    }

    @PostMapping("category")
    public void saveCategory(@RequestBody CategoryRestModel category)
    {
        categoryService.saveCategory(category);
    }




    }
