package com.kowalczyk.hurtownia.controller.employees;

import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.representationModel.employees.EmployeeRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.employees.EmployeeRestModel;
import com.kowalczyk.hurtownia.model.responses.employees.JobPositionEmployeeRestModel;
import com.kowalczyk.hurtownia.model.responses.employees.UserAccountRestModel;
import com.kowalczyk.hurtownia.model.services.employees.EmployeeService;
import com.kowalczyk.hurtownia.model.services.employees.JobPositionEmployeeService;
import com.kowalczyk.hurtownia.model.services.employees.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<EmployeeRepresentationModel> getUser(
            @AuthenticationPrincipal UserAccount userAccount)
    {
        EmployeeRepresentationModel employeeRepresentationModel =
                employeeService.getById(userAccount);

        if(employeeRepresentationModel==null)
        {
            return new ResponseEntity<EmployeeRepresentationModel>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employeeRepresentationModel);

    }

    @GetMapping("employees")
    public ResponseEntity<List<EmployeeRepresentationModel>> getAllEmployees()
    {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("employee/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeRestModel employeeRestModel)
    {
        try{
            employeeService.saveEmployee(employeeRestModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @PatchMapping("employee/employee/{id}")
    public ResponseEntity<?> patchUpdateEmployee(@RequestBody EmployeeRestModel employeeRestModel
            , @PathVariable("id") Long id) {
        try{
            employeeService.patchEmployee(employeeRestModel,id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("employee/employee/{id}")
    public ResponseEntity<?> putUpdateEmployee(@RequestBody EmployeeRestModel employeeRestModel
            , @PathVariable("id") Long id) {
        try{
            employeeService.putEmployee(employeeRestModel,id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id)
    {
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);

        }
    }

    //UserAccount

    @PostMapping("employee/userAccount")
    public ResponseEntity<?> saveUserAcounnt(@RequestBody UserAccountRestModel userAccountRestModel)
    {
        try{
            userAccountService.saveUser(userAccountRestModel,passwordEncoder);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @PatchMapping("employee/userAccount/{username}")
    public ResponseEntity<?> patchUpdateUser(@RequestBody UserAccountRestModel userAccountRestModel
            , @PathVariable("username") String username) {
        try{
            userAccountService.patchUser(userAccountRestModel,username,passwordEncoder);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("employee/userAccount/{username}")
    public ResponseEntity<?> putUpdateUser(@RequestBody UserAccountRestModel userAccountRestModel
            , @PathVariable("username") String username) {

        try{
            userAccountService.putUser(userAccountRestModel,username,passwordEncoder);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }

    }

    //JobPositionEmployee

    @PostMapping("employee/position")
    public ResponseEntity<?> saveJobPositionEmployee(@RequestBody JobPositionEmployeeRestModel jobPositionEmployeeRestModel)
    {
        try{
            jobPositionEmployeeService.saveJob(jobPositionEmployeeRestModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @PatchMapping("employee/position/{jobId}/conf/{employeeId}")
    public ResponseEntity<?> patchUpdateJobPositionEmployee(@RequestBody JobPositionEmployeeRestModel jobPositionEmployeeRestModel
            , @PathVariable("employeeId") Long employeeId
            , @PathVariable("jobId") Long jobId) {

        try{
            jobPositionEmployeeService.patchJob(jobPositionEmployeeRestModel,employeeId,jobId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("employee/position/{jobId}/conf/{employeeId}")
    public ResponseEntity<?> putUpdateJobPositionEmployee(@RequestBody JobPositionEmployeeRestModel jobPositionEmployeeRestModel
            , @PathVariable("employeeId") Long employeeId
            , @PathVariable("jobId") Long jobId) {
        try{
            jobPositionEmployeeService.putJob(jobPositionEmployeeRestModel,employeeId,jobId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }

    }





}
