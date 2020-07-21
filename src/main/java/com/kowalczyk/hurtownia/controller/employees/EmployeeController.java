package com.kowalczyk.hurtownia.controller.employees;

import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.representationModel.EmployeeRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.employees.EmployeeRestModel;
import com.kowalczyk.hurtownia.model.responses.employees.JobPositionEmployeeRestModel;
import com.kowalczyk.hurtownia.model.responses.employees.UserAccountRestModel;
import com.kowalczyk.hurtownia.model.services.employees.EmployeeService;
import com.kowalczyk.hurtownia.model.services.employees.JobPositionEmployeeService;
import com.kowalczyk.hurtownia.model.services.employees.UserAccountService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JobPositionEmployeeService jobPositionEmployeeService;
    private final UserAccountService userAccountService;

    public EmployeeController(EmployeeService employeeService, JobPositionEmployeeService jobPositionEmployeeService, UserAccountService userAccountService) {
        this.employeeService = employeeService;
        this.jobPositionEmployeeService = jobPositionEmployeeService;
        this.userAccountService = userAccountService;
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
        jobPositionEmployeeService.saveJob(jobPositionEmployeeRestModel);
    }

    @PostMapping("employee/employee")
    public void saveEmployee(@RequestBody EmployeeRestModel employeeRestModel)
    {
        employeeService.saveEmployee(employeeRestModel);
    }

    @PostMapping("employee/userAccount")
    public void saveUserAcounnt(@RequestBody UserAccountRestModel userAccountRestModel)
    {
        userAccountService.saveUser(userAccountRestModel);
    }

}
