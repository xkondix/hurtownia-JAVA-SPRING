package com.kowalczyk.hurtownia.model.representationModel.employees;

import com.kowalczyk.hurtownia.model.entities.employees.JobPosition;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class JobPositionRepresentationModel {

    @Getter
    private final String nameOfPosition;
    @Getter
    private final List<List<String>> employees;


    public JobPositionRepresentationModel(JobPosition jobPosition) {
        this.nameOfPosition = jobPosition.getNameOfPosition();
        this.employees = jobPosition.getEmployees().stream()
                .map(employee -> employee.getListForPosition())
                .collect(Collectors.toList());
    }
}
