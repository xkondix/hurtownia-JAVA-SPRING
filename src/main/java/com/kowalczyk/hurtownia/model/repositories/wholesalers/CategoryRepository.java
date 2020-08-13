package com.kowalczyk.hurtownia.model.repositories.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByNameOfCategory(String name);
}
