package com.kowalczyk.hurtownia.controller;

import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.responses.CategoryRestModel;
import com.kowalczyk.hurtownia.model.responses.ProductRestModel;
import com.kowalczyk.hurtownia.model.services.CategoryService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("api")
public class CategoryController {



    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public CollectionModel<EntityModel<CategoryRestModel>> getAll()
    {

        List<EntityModel<CategoryRestModel>> categories = StreamSupport.stream(categoryService.getAll().spliterator(), false)
                .map(this::createCategory).collect(Collectors.toList());
                        //EntityModel.of(category,
//                        linkTo(methodOn(CategoryController.class).getById(category.getId())).withSelfRel(),
//                        linkTo(methodOn(CategoryController.class).getAll()).withRel("categories")))
//                .collect(Collectors.toList());

        return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).getAll()).withSelfRel());
        //return new ResponseEntity<Iterable<CategoryRestModel>>(categoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("category")
    public void saveCategory(@RequestBody CategoryRestModel category)
    {
        categoryService.saveCategory(category);
    }

    @GetMapping("category/{id}")
    public EntityModel<CategoryRestModel> getById(@PathVariable Long id)
    {
        CategoryRestModel categoryRestModel =categoryService.getById(id);

        categoryRestModel.setProducts2(categoryRestModel.getProducts().stream().map(product -> EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getById(product.getId())).withSelfRel()))
                .collect(Collectors.toSet()));
        return EntityModel.of(categoryRestModel, //
                linkTo(methodOn(CategoryController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(CategoryController.class).getAll()).withRel("employees"));

        //return new ResponseEntity<CategoryRestModel>(categoryService.getById(id), HttpStatus.OK);
    }

    private EntityModel<CategoryRestModel> createCategory(CategoryRestModel categoryRestModel)
    {

        categoryRestModel.setProducts2(categoryRestModel.getProducts().stream().map(product -> EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getById(product.getId())).withSelfRel()))
                .collect(Collectors.toSet()));
        return EntityModel.of(categoryRestModel, //
                linkTo(methodOn(CategoryController.class).getById(categoryRestModel.getId())).withSelfRel(),
                linkTo(methodOn(CategoryController.class).getAll()).withRel("employees"));
    }



    }
