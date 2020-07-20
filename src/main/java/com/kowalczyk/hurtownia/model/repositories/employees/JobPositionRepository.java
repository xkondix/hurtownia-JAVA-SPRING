package com.kowalczyk.hurtownia.model.repositories.employees;

import com.kowalczyk.hurtownia.model.entities.employees.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionRepository extends JpaRepository<JobPosition,Long> {
}
