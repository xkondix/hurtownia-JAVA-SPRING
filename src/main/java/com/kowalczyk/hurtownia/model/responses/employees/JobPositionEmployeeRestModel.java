package com.kowalczyk.hurtownia.model.responses.employees;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import com.kowalczyk.hurtownia.model.entities.employees.JobPosition;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class JobPositionEmployeeRestModel {
    private final Double reward;
    private final Date dataOfStart;
    private final Date dataOfEnd;
    private final Long position;
    private final Long employee;

    public JobPositionEmployeeRestModel(Double reward, String dataOfStart, String dataOfEnd, Long position, Long employee) {
        this.reward = reward;
        this.dataOfStart = new Date(dataOfStart);
        this.dataOfEnd = new Date(dataOfEnd);
        this.position = position;
        this.employee = employee;
    }
}
