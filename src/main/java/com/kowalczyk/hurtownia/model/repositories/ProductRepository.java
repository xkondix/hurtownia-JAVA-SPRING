package com.kowalczyk.hurtownia.model.repositories;

import com.kowalczyk.hurtownia.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findAllByCategoryId(long id);

    public Optional<Product> findByNameOfProduct(String name);

    public Optional<Product> findByBrand(String name);




}
