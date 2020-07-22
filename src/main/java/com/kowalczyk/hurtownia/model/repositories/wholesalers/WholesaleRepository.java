package com.kowalczyk.hurtownia.model.repositories.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholesaleRepository extends JpaRepository<Wholesale,Long> {
}
