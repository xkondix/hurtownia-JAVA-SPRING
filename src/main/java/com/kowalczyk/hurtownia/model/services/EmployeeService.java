package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import com.kowalczyk.hurtownia.model.entities.employees.JobPosition;
import com.kowalczyk.hurtownia.model.entities.employees.JobPositionEmployee;
import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.repositories.employees.EmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionEmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionRepository;
import com.kowalczyk.hurtownia.model.representationModel.EmployeeRepresentationModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private  final  EmployeeRepository employeeRepository;
    private final JobPositionEmployeeRepository jobPositionEmployeeRepository;
    private final JobPositionRepository jobPositionRepository;

    public EmployeeService(EmployeeRepository employeeRepository, JobPositionEmployeeRepository jobPositionEmployeeRepository, JobPositionRepository jobPositionRepository) {
        this.employeeRepository = employeeRepository;
        this.jobPositionEmployeeRepository = jobPositionEmployeeRepository;
        this.jobPositionRepository = jobPositionRepository;
    }

    public EmployeeRepresentationModel getById(UserAccount userAccount)
    {
        Employee employee = employeeRepository.findByUserAccountId(userAccount.getId());
        return new EmployeeRepresentationModel(employee,userAccount);
    }

}
