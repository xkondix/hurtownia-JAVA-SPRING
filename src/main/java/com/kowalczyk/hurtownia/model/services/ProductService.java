package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.repositories.CategoryRespository;
import com.kowalczyk.hurtownia.model.repositories.ProductRepository;
import com.kowalczyk.hurtownia.model.responses.ProductRestModel;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRespository categoryRespository;


    public ProductService(final ProductRepository productRepository, CategoryRespository categoryRespository){
        this.productRepository = productRepository;
        this.categoryRespository = categoryRespository;
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public void saveProduct(ProductRestModel product) {
        productRepository.save(mapRestModel(product));
    }

    private Product mapRestModel(final ProductRestModel model) {
        return new Product(model.getNameOfProduct(),model.getBrand(),model.getPricePerItem()
        ,model.getProductCode(),categoryRespository.findById(1l).get());
    }



}

