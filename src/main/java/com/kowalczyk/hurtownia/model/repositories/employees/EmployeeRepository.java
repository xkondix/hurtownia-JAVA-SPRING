package com.kowalczyk.hurtownia.model.repositories.employees;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public Employee findByUserAccountId(Long id);
    public List<Employee> findByWholesaleId(Long id);

}
