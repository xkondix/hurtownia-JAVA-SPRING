package com.kowalczyk.hurtownia.model.repositories;

import com.kowalczyk.hurtownia.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRespository extends JpaRepository<Category, Long> {



}
