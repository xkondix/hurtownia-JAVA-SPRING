package com.kowalczyk.hurtownia.controller.wholesalers;

import com.kowalczyk.hurtownia.model.representationModel.wholesalers.CategoryRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.wholesalers.CategoryRestModel;
import com.kowalczyk.hurtownia.model.services.wholesalers.CategoryService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
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


    // .getByName(name.replace("_"," ")) if name have whitespace
    // reverse change in class CategoryRepresentationModelAssembler, method -> createModelWithId
    // HATEOAS need this
    @GetMapping("category/name/{name}")
    public ResponseEntity<CategoryRepresentationModel> getByName(@PathVariable String name) {
        CategoryRepresentationModel category = categoryService.getByName(name.replace("_"," "));
        if(category==null)
        {
            return new ResponseEntity<CategoryRepresentationModel>(HttpStatus.NOT_FOUND);
        }
        category.add(linkTo(methodOn(CategoryController.class).getAll()).withSelfRel());
        return ResponseEntity.ok(category);
    }

    @PostMapping("category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRestModel category)
    {
        try{
            categoryService.saveCategory(category);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("category/{id}")
    public ResponseEntity<CategoryRepresentationModel> getById(@PathVariable Long id)
    {
        CategoryRepresentationModel category = categoryService.getById(id);
        if(category==null)
        {
            return new ResponseEntity<CategoryRepresentationModel>(HttpStatus.NOT_FOUND);
        }
        category.add(linkTo(methodOn(CategoryController.class).getAll()).withSelfRel());
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id)
    {
        try{
            categoryService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);

        }
    }


    }
