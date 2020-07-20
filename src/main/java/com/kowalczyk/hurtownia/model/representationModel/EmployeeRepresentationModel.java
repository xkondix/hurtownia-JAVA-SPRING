package com.kowalczyk.hurtownia.model.representationModel;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import com.kowalczyk.hurtownia.model.entities.employees.JobPosition;
import com.kowalczyk.hurtownia.model.entities.employees.JobPositionEmployee;
import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


public class EmployeeRepresentationModel {

    @Getter
    private final String name;
    @Getter
    private final String surename;
    @Getter
    private final String username;
    @Getter
    private final String password;
    @Getter
    private final List<List<String>> jobs;



    public EmployeeRepresentationModel(Employee employee, UserAccount userAccount) {
        this.name=employee.getName();
        this.surename=employee.getSurename();
        this.username=userAccount.getUsername();
        this.password=userAccount.getPassword();
        this.jobs=employee.getJobs().stream().map(job -> job.getListForEmployee()).collect(Collectors.toList());
    }
}
