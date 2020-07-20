package com.kowalczyk.hurtownia.controller;

import com.kowalczyk.hurtownia.model.representationModel.CategoryRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.CategoryRestModel;
import com.kowalczyk.hurtownia.model.services.CategoryService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class CategoryController {



    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public ResponseEntity<CollectionModel<CategoryRepresentationModel>> getAll() {
        return ResponseEntity.ok(CollectionModel.of(
                categoryService.getAll(),
                linkTo(methodOn(CategoryController.class).getAll()).withSelfRel()));
    }

    @PostMapping("category")
    public void saveCategory(@RequestBody CategoryRestModel category)
    {
        categoryService.saveCategory(category);
    }

    @GetMapping("category/{id}")
    public ResponseEntity<CategoryRepresentationModel> getById(@PathVariable Long id)
    {
        CategoryRepresentationModel category = categoryService.getById(id);
        category.add(linkTo(methodOn(CategoryController.class).getAll()).withSelfRel());
        return ResponseEntity.ok(category);
    }


    }
