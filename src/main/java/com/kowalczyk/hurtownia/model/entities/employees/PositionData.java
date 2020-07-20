package com.kowalczyk.hurtownia.model.entities.employees;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PositionData {

    private final java.util.Date dataOfStart;
    private final java.util.Date dataOfEnd;

    @EmbeddedId
    private PositionDataId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("EmployeeId")
    private Employee employee;


    public PositionData(Date dataOfStart, Date dataOfEnd, PositionDataId id, Position position, Employee employee) {
        this.dataOfStart = dataOfStart;
        this.dataOfEnd = dataOfEnd;
        this.id = new PositionDataId(employee.getId(),position.getId());
        this.position = position;
        this.employee = employee;
    }


}
