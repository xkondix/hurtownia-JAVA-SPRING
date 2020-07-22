package com.kowalczyk.hurtownia.model.representationModel.wholesalers;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WholesaleRepresentationModel {

    @Getter
    private final String nameOfWholesale;
    @Getter
    private final List<List<String>> products;
    @Getter
    private final List<Employee> employees;

    public WholesaleRepresentationModel(Wholesale wholesale) {
        this.nameOfWholesale=wholesale.getNameOfWholesale();
        this.products = wholesale.getProducts().stream()
                .map(product -> product.getListForWholesale())
                .collect(Collectors.toList());
        this.employees = wholesale.getEmployees();
    }
}
