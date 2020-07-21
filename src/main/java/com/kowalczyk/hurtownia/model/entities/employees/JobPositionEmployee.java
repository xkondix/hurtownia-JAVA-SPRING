package com.kowalczyk.hurtownia.model.entities.employees;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class JobPositionEmployee {

    private final Double reward;
    private final java.util.Date dataOfStart;
    private final java.util.Date dataOfEnd;


    @EmbeddedId
    private JobPositionEmployeeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    private JobPosition position;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    private Employee employee;


    public JobPositionEmployee(Date dataOfStart, Date dataOfEnd, Double reward, JobPosition position, Employee employee) {
        this.dataOfStart = dataOfStart;
        this.dataOfEnd = dataOfEnd;
        this.reward = reward;
        this.id = new JobPositionEmployeeId(employee.getId(),position.getId());
        this.position = position;
        this.employee = employee;
    }

    public List<String> getListForEmployee()
    {
        return new ArrayList<String>(Arrays.asList("Position : "+position.getNameOfPosition(),
                "Reward : "+reward
                ,"DataOfStart : "+dataOfStart
                ,"DataOfEnd : "+dataOfEnd));
    }

    public List<String> getListForPosition()
    {
        return new ArrayList<String>(Arrays.asList(
                "Employee : "+employee.getName()+" "+employee.getSurename()
                , "Reward : "+reward
                ,"DataOfStart : "+dataOfStart
                ,"DataOfEnd : "+dataOfEnd));
    }

    public String toString()
    {
        return "JobPositionEmployee";
    }


}
