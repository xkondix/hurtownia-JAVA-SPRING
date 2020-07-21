package com.kowalczyk.hurtownia.model.responses.employees;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EmployeeRestModel {

    private final String name;
    private final String surename;
    private final Long userAccount;

    public EmployeeRestModel(String name, String surename, Long userAccount) {
        this.name = name;
        this.surename = surename;
        this.userAccount = userAccount;
    }


}
