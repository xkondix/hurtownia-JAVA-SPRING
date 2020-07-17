package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.repositories.CategoryRepository;
import com.kowalczyk.hurtownia.model.repositories.ProductRepository;
import com.kowalczyk.hurtownia.model.representationModel.CategoryRepresentationModel;
import com.kowalczyk.hurtownia.model.resourceAssembler.CategoryRepresentationModelAssembler;
import com.kowalczyk.hurtownia.model.responses.CategoryRestModel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    private final CategoryRepository categoryRespository;
    private final ProductRepository productRepository;


    public CategoryService(CategoryRepository categoryRespository, ProductRepository productRepository) {
        this.categoryRespository = categoryRespository;
        this.productRepository = productRepository;
    }

    public List<CategoryRepresentationModel> getAll() {
        return categoryRespository.findAll().stream().map
                (x -> getById(x.getId()))
                .collect(Collectors.toList());
    }

    public void saveCategory(CategoryRestModel category) {
        categoryRespository.save(mapRestModel(category));
    }

    public CategoryRepresentationModel getById(Long id)
    {
        Optional<Category> category = categoryRespository.findById(id);
        if(category.isPresent())
        {
            CategoryRepresentationModelAssembler categoryRepresentationModelAssembler =
                    new CategoryRepresentationModelAssembler("category");
            categoryRepresentationModelAssembler.setProducts(findAllByCategoryId(category.get().getId()));
            return categoryRepresentationModelAssembler.toModel(category.get());
        }

        return null;
    }


    //methods

    private Category mapRestModel(final CategoryRestModel model) {
        return new Category(model.getNameOfCategory());
    }


    private List<Product> findAllByCategoryId(Long id)
    {
        return productRepository.findAllByCategoryId(id);
    }

}
