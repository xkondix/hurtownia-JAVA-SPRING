package com.kowalczyk.hurtownia.model.repositories;

import com.kowalczyk.hurtownia.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public Employee findByUserAccountId(Long id);
}
