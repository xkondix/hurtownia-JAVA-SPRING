package com.kowalczyk.hurtownia.model.repositories;

import com.kowalczyk.hurtownia.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
