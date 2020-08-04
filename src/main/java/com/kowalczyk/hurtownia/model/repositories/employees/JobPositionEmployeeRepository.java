package com.kowalczyk.hurtownia.model.repositories.employees;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import com.kowalczyk.hurtownia.model.entities.employees.JobPosition;
import com.kowalczyk.hurtownia.model.entities.employees.JobPositionEmployee;
import com.kowalczyk.hurtownia.model.entities.employees.JobPositionEmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface JobPositionEmployeeRepository extends JpaRepository<JobPositionEmployee, Long>{

    public List<JobPositionEmployee> findAllByEmployeeId(Long id);

    Optional<JobPositionEmployee> findByEmployeeIdAndPositionId(Long employeeId, Long positionId);
}
