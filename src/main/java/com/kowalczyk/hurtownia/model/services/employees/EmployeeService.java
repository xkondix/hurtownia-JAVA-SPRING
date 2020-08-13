package com.kowalczyk.hurtownia.model.services.employees;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import com.kowalczyk.hurtownia.model.repositories.employees.EmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionEmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.UserAccountRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.WholesaleRepository;
import com.kowalczyk.hurtownia.model.representationModel.employees.EmployeeRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.employees.EmployeeRestModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserAccountRepository userAccountRepository;
    private final WholesaleRepository wholesaleRepository;
    private final JobPositionEmployeeRepository jobPositionEmployeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository, JobPositionEmployeeRepository jobPositionEmployeeRepository, JobPositionRepository jobPositionRepository, UserAccountRepository userAccountRepository, WholesaleRepository wholesaleRepository, JobPositionEmployeeRepository jobPositionEmployeeRepository1) {
        this.employeeRepository = employeeRepository;
        this.userAccountRepository = userAccountRepository;
        this.wholesaleRepository = wholesaleRepository;
        this.jobPositionEmployeeRepository = jobPositionEmployeeRepository1;
    }

    public EmployeeRepresentationModel getById(UserAccount userAccount)
    {
        Employee employee = employeeRepository.findByUserAccountId(userAccount.getId());
        Optional<Wholesale> wholesale = wholesaleRepository
                .findById(employee.getWholesale().getId());
        return new EmployeeRepresentationModel(employee,userAccount, wholesale.get().getNameOfWholesale());
    }

    public void saveEmployee(EmployeeRestModel employeeRestModel) {
        employeeRepository.save(mapToEnity(employeeRestModel));
    }

    public void patchEmployee(EmployeeRestModel employeeRestModel, Long id) {

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent())
        {
            Employee employee = employeeOptional.get();
            if(employeeRestModel.getSurename()!=null)
            {
                employee.setSurename(employeeRestModel.getSurename());
            }
            if(employeeRestModel.getUserAccount()!=null)
            {
                employee.setUserAccount(userAccountRepository.
                        findById(employeeRestModel.getUserAccount()).get());
            }
            if(employeeRestModel.getWholesale()!=null)
            {
                employee.setWholesale(wholesaleRepository.findById(
                        employeeRestModel.getWholesale()).get());
            }
            employeeRepository.save(employee);
        }

    }


    public void putEmployee(EmployeeRestModel employeeRestModel, Long id) {
        Employee employee = mapToEnity(employeeRestModel);
        employee.setId(id);
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent())
        {
            jobPositionEmployeeRepository.findAllByEmployeeId(employee.get().getId())
            .forEach(job -> jobPositionEmployeeRepository.delete(job));

            userAccountRepository.delete(userAccountRepository.findById(
                    employee.get().getUserAccount().getId()).get());

            employeeRepository.delete(employee.get());

        }

    }

    public List<EmployeeRepresentationModel> getAllEmployees() {

        return employeeRepository.findAll().stream().map(employee ->
                new EmployeeRepresentationModel(
                        employee,employee.getUserAccount(),employee.getWholesale().getNameOfWholesale()))
                .collect(Collectors.toList());

    }



    //methods

    private Employee mapToEnity(EmployeeRestModel employeeRestModel) {
        Optional<UserAccount> user =
                userAccountRepository.findById(employeeRestModel.getUserAccount());
        Optional<Wholesale> wholesale =
                wholesaleRepository.findById(employeeRestModel.getWholesale());
        if(user.isPresent() && wholesale.isPresent()) {
            return new Employee(employeeRestModel.getName()
                    , employeeRestModel.getSurename()
                    , user.get(),wholesale.get());
        }
        else if(!user.isPresent() && wholesale.isPresent())
        {
            return new Employee(employeeRestModel.getName()
                    , employeeRestModel.getSurename()
                    ,null,wholesale.get());
        }
        else if(user.isPresent() && (!wholesale.isPresent()))
        {
            return new Employee(employeeRestModel.getName()
                    , employeeRestModel.getSurename()
                    ,user.get(),null);
        }
        else
        {
            return new Employee(employeeRestModel.getName()
                    , employeeRestModel.getSurename()
                    ,null,null);
        }
    }


}
