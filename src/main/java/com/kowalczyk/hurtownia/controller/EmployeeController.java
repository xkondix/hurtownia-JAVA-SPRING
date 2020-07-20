package com.kowalczyk.hurtownia.controller;

import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.resourceAssembler.EmployeeRepresentationModel;
import com.kowalczyk.hurtownia.model.services.EmployeeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employee")
    public EmployeeRepresentationModel getUser(
            @AuthenticationPrincipal UserAccount userAccount)
    {
        return employeeService.getById(userAccount);
    }

}
