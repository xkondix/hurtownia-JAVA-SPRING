package com.kowalczyk.hurtownia.model.services.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import com.kowalczyk.hurtownia.model.repositories.employees.EmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.WholesaleRepository;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.WholesaleRepresentationModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WholesaleService {

    private final WholesaleRepository wholesaleRepository;
    private final EmployeeRepository employeeRepository;

    public WholesaleService(WholesaleRepository wholesaleRepository, EmployeeRepository employeeRepository) {
        this.wholesaleRepository = wholesaleRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<WholesaleRepresentationModel> getAll() {
        return wholesaleRepository.findAll()
                .stream()
                .map(wholesale -> new WholesaleRepresentationModel(wholesale,getEmployees(wholesale.getId())))
                .collect(Collectors.toList());

    }

    public WholesaleRepresentationModel getById(Long id) {

        Optional<Wholesale> wholesale = wholesaleRepository.findById(id);
        return wholesale.isPresent() ? new WholesaleRepresentationModel(
                wholesale.get(),getEmployees(wholesale.get().getId())) : null;

    }


    //methods

    private List<String> getEmployees(Long id)
    {
        return employeeRepository.findByWholesaleId(id).stream().map
                ( employee -> employee.getName() +" "+employee.getSurename())
                .collect(Collectors.toList());
    }


}
