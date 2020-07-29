package com.kowalczyk.hurtownia.model.repositories.client;

import com.kowalczyk.hurtownia.model.entities.client.OrderSupply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSupplyRepository extends JpaRepository<OrderSupply,Long> {
}
