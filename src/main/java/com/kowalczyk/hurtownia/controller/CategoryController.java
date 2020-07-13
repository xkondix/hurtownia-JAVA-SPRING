package com.kowalczyk.hurtownia.controller;

import com.kowalczyk.hurtownia.model.responses.CategoryRestModel;
import com.kowalczyk.hurtownia.model.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class CategoryController {



    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public ResponseEntity<Iterable<CategoryRestModel>> getAll()
    {
        return new ResponseEntity<Iterable<CategoryRestModel>>(categoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("category")
    public void saveCategory(@RequestBody CategoryRestModel category)
    {
        categoryService.saveCategory(category);
    }

    @GetMapping("category/{id}")
    public ResponseEntity<CategoryRestModel> getById(@PathVariable Long id)
    {
        return new ResponseEntity<CategoryRestModel>(categoryService.getById(id), HttpStatus.OK);
    }



    }
