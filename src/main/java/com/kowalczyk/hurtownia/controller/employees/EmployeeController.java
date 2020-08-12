package com.kowalczyk.hurtownia.controller.employees;

import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.representationModel.employees.EmployeeRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.employees.EmployeeRestModel;
import com.kowalczyk.hurtownia.model.responses.employees.JobPositionEmployeeRestModel;
import com.kowalczyk.hurtownia.model.responses.employees.UserAccountRestModel;
import com.kowalczyk.hurtownia.model.services.employees.EmployeeService;
import com.kowalczyk.hurtownia.model.services.employees.JobPositionEmployeeService;
import com.kowalczyk.hurtownia.model.services.employees.UserAccountService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JobPositionEmployeeService jobPositionEmployeeService;
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeController(EmployeeService employeeService, JobPositionEmployeeService jobPositionEmployeeService, UserAccountService userAccountService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.jobPositionEmployeeService = jobPositionEmployeeService;
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    //Employee

    @GetMapping("employee")
    public EmployeeRepresentationModel getUser(
            @AuthenticationPrincipal UserAccount userAccount)
    {
        return employeeService.getById(userAccount);
    }

    @PostMapping("employee/employee")
    public void saveEmployee(@RequestBody EmployeeRestModel employeeRestModel)
    {
        employeeService.saveEmployee(employeeRestModel);
    }

    @PatchMapping("/employee/employee/{id}")
    public void patchUpdateEmployee(@RequestBody EmployeeRestModel employeeRestModel
            , @PathVariable("id") Long id) {
        employeeService.patchEmployee(employeeRestModel,id);
    }

    @PutMapping("/employee/employee/{id}")
    public void putUpdateEmployee(@RequestBody EmployeeRestModel employeeRestModel
            , @PathVariable("id") Long id) {
        employeeService.putEmployee(employeeRestModel,id);
    }

    @DeleteMapping("employee/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        employeeService.deleteEmployee(id);
    }

    //UserAccount

    @PostMapping("employee/userAccount")
    public void saveUserAcounnt(@RequestBody UserAccountRestModel userAccountRestModel)
    {
        userAccountService.saveUser(userAccountRestModel,passwordEncoder);
    }

    @PatchMapping("/employee/userAccount/{username}")
    public void patchUpdateUser(@RequestBody UserAccountRestModel userAccountRestModel
            , @PathVariable("username") String username) {
        userAccountService.patchUser(userAccountRestModel,username,passwordEncoder);
    }

    @PutMapping("/employee/userAccount/{username}")
    public void putUpdateUser(@RequestBody UserAccountRestModel userAccountRestModel
            , @PathVariable("username") String username) {
        userAccountService.putUser(userAccountRestModel,username,passwordEncoder);

    }

    //JobPositionEmployee

    @PostMapping("employee/position")
    public void saveJobPositionEmployee(@RequestBody JobPositionEmployeeRestModel jobPositionEmployeeRestModel)
    {
        jobPositionEmployeeService.saveJob(jobPositionEmployeeRestModel);
    }

    @PatchMapping("/employee/position/{jobId}/conf/{employeeId}")
    public void patchUpdateJobPositionEmployee(@RequestBody JobPositionEmployeeRestModel jobPositionEmployeeRestModel
            , @PathVariable("employeeId") Long employeeId
            , @PathVariable("jobId") Long jobId) {
        jobPositionEmployeeService.patchJob(jobPositionEmployeeRestModel,employeeId,jobId);
    }

    @PutMapping("/employee/position/{jobId}/conf/{employeeId}")
    public void putUpdateJobPositionEmployee(@RequestBody JobPositionEmployeeRestModel jobPositionEmployeeRestModel
            , @PathVariable("employeeId") Long employeeId
            , @PathVariable("jobId") Long jobId) {
        jobPositionEmployeeService.putJob(jobPositionEmployeeRestModel,employeeId,jobId);

    }





}
