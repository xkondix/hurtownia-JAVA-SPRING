package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.repositories.ProductRepository;
import com.kowalczyk.hurtownia.model.responses.ProductRestModel;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public void save(final Product product) {
        productRepository.save(product);
    }



}

