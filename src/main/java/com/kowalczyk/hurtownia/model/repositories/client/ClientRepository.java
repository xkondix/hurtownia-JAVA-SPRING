package com.kowalczyk.hurtownia.model.repositories.client;

import com.kowalczyk.hurtownia.model.entities.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
