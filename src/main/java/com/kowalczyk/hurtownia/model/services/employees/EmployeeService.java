package com.kowalczyk.hurtownia.model.services.employees;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.repositories.employees.EmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionEmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.UserAccountRepository;
import com.kowalczyk.hurtownia.model.representationModel.EmployeeRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.employees.EmployeeRestModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserAccountRepository userAccountRepository;


    public EmployeeService(EmployeeRepository employeeRepository, JobPositionEmployeeRepository jobPositionEmployeeRepository, JobPositionRepository jobPositionRepository, UserAccountRepository userAccountRepository) {
        this.employeeRepository = employeeRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public EmployeeRepresentationModel getById(UserAccount userAccount)
    {
        Employee employee = employeeRepository.findByUserAccountId(userAccount.getId());
        return new EmployeeRepresentationModel(employee,userAccount);
    }

    public void saveEmployee(EmployeeRestModel employeeRestModel) {
        employeeRepository.save(mapToEnity(employeeRestModel));
    }



    //methods

    private Employee mapToEnity(EmployeeRestModel employeeRestModel) {
        Optional<UserAccount> user =
                userAccountRepository.findById(employeeRestModel.getUserAccount());
        System.out.println(user.get());
        if(user.isPresent()) {
            return new Employee(employeeRestModel.getName()
                    , employeeRestModel.getSurename()
                    , user.get());
        }
        else
        {
            return new Employee(employeeRestModel.getName()
                    , employeeRestModel.getSurename()
                    ,null);
        }
    }

}
