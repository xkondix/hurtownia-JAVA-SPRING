package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.repositories.CategoryRespository;
import com.kowalczyk.hurtownia.model.repositories.ProductRepository;
import com.kowalczyk.hurtownia.model.responses.CategoryRestModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
public class CategoryService {

    private final CategoryRespository categoryRespository;
    private final ProductRepository productRepository;


    public CategoryService(CategoryRespository categoryRespository, ProductRepository productRepository) {
        this.categoryRespository = categoryRespository;
        this.productRepository = productRepository;
    }

    public Iterable<CategoryRestModel> getAll() {
        return mapListRestModel(categoryRespository.findAll());
    }

    public void saveCategory(CategoryRestModel category) {
        categoryRespository.save(mapRestModel(category));
    }

    private Category mapRestModel(final CategoryRestModel model) {
        return new Category(model.getNameOfCategory());
    }

    private Iterable<CategoryRestModel> mapListRestModel(final Iterable<Category> model) {
        return StreamSupport.stream(model.spliterator(), false).
                map(x -> new CategoryRestModel(x,findAllByCategoryId(x.getId())))
                .collect(Collectors.toList());
    }


    private Set<Product> findAllByCategoryId(Long id)
    {
        return StreamSupport.stream((productRepository.findAllByCategoryId(id).spliterator()), true).collect(Collectors.toSet());
    }
}
