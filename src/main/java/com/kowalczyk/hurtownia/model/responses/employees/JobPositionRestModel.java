package com.kowalczyk.hurtownia.model.responses.employees;

import com.kowalczyk.hurtownia.model.entities.employees.JobPosition;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class JobPositionRestModel {

    private final String nameOfPosition;

    public JobPositionRestModel(String nameOfPosition) {
        this.nameOfPosition = nameOfPosition;
    }

    public JobPosition mapToEntity() {
        return new JobPosition(nameOfPosition);
    }
}
