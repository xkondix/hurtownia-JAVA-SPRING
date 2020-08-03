package com.kowalczyk.hurtownia.model.repositories.client;

import com.kowalczyk.hurtownia.model.entities.client.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Long> {
}
