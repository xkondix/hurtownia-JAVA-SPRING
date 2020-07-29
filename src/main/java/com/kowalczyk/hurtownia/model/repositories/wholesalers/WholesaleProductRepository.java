package com.kowalczyk.hurtownia.model.repositories.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.WholesaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholesaleProductRepository extends JpaRepository<WholesaleProduct,Long> {
}
