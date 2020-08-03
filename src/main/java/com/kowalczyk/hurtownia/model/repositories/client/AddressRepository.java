package com.kowalczyk.hurtownia.model.repositories.client;

import com.kowalczyk.hurtownia.model.entities.client.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
