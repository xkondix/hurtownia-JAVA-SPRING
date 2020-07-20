package com.kowalczyk.hurtownia.controller;

import com.kowalczyk.hurtownia.model.entities.employees.JobPositionEmployee;
import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.representationModel.EmployeeRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.JobPositionEmployeeRestModel;
import com.kowalczyk.hurtownia.model.services.EmployeeService;
import com.kowalczyk.hurtownia.model.services.JobPositionEmployeeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JobPositionEmployeeService jobPositionEmployeeService;

    public EmployeeController(EmployeeService employeeService, JobPositionEmployeeService jobPositionEmployeeService) {
        this.employeeService = employeeService;
        this.jobPositionEmployeeService = jobPositionEmployeeService;
    }

    @GetMapping("employee")
    public EmployeeRepresentationModel getUser(
            @AuthenticationPrincipal UserAccount userAccount)
    {
        return employeeService.getById(userAccount);
    }

    @PostMapping("employee/job")
    public void saveJob(@RequestBody JobPositionEmployeeRestModel jobPositionEmployeeRestModel)
    {
        jobPositionEmployeeService.save(jobPositionEmployeeRestModel);
    }

}
