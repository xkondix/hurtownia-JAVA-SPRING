package com.kowalczyk.hurtownia.model.services.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Category;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;

import com.kowalczyk.hurtownia.model.repositories.wholesalers.CategoryRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.ProductRepository;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.CategoryRepresentationModel;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductRepresentationModel;
import com.kowalczyk.hurtownia.model.resourceAssembler.CategoryRepresentationModelAssembler;
import com.kowalczyk.hurtownia.model.resourceAssembler.ProductRepresentationModelAssembler;
import com.kowalczyk.hurtownia.model.responses.wholesalers.CategoryRestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    private final CategoryRepository categoryRespository;
    private final ProductRepository productRepository;


    @Autowired
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
        categoryRespository.save(mapToEntity(category));
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

    public void deleteProduct(Long id) throws Exception {

        Optional<Category> category  = categoryRespository.findById(id);
        if(category.isPresent())
        {
            categoryRespository.delete(category.get());
        }
        else
        {
            throw new Exception();
        }

    }

    public CategoryRepresentationModel getByName(String name)
    {
        Optional<Category> category = categoryRespository.findByNameOfCategory(name);
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

    private Category mapToEntity(final CategoryRestModel model) {
        return new Category(model.getNameOfCategory());
    }


    private List<Product> findAllByCategoryId(Long id)
    {
        return productRepository.findAllByCategoryId(id);
    }



}
