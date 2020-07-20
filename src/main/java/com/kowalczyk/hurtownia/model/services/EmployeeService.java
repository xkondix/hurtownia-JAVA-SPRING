package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.repositories.EmployeeRepository;
import com.kowalczyk.hurtownia.model.resourceAssembler.EmployeeRepresentationModel;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private  final  EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeRepresentationModel getById(UserAccount userAccount)
    {
        Employee employee = employeeRepository.findByUserAccountId(userAccount.getId());
        return new EmployeeRepresentationModel(employee,userAccount);
    }

}
