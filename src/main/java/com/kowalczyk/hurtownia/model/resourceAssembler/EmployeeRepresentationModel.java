package com.kowalczyk.hurtownia.model.resourceAssembler;

import com.kowalczyk.hurtownia.model.entities.Employee;
import com.kowalczyk.hurtownia.model.entities.UserAccount;
import lombok.Getter;


public class EmployeeRepresentationModel {

    @Getter
    private final String name;
    @Getter
    private final String surename;
    @Getter
    private final String username;
    @Getter
    private final String password;



    public EmployeeRepresentationModel(Employee employee, UserAccount userAccount) {
        this.name=employee.getName();
        this.surename=employee.getSurename();
        this.username=userAccount.getUsername();
        this.password=userAccount.getPassword();
    }
}
