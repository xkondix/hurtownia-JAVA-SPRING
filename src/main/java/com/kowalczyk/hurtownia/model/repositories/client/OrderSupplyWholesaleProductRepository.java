package com.kowalczyk.hurtownia.model.repositories.client;

import com.kowalczyk.hurtownia.model.entities.client.OrderSupllyWholesaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSupplyWholesaleProductRepository
        extends JpaRepository<OrderSupllyWholesaleProduct,Long> {
}
