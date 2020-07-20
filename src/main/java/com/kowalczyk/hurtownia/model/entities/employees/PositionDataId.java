package com.kowalczyk.hurtownia.model.entities.employees;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PositionDataId implements Serializable {


    @Column(name = "employee_id")
    private final Long empoyeeId;

    @Column(name = "position_id")
    private final Long positonId;

    public PositionDataId(Long empoyeeId, Long positonId) {
        this.empoyeeId = empoyeeId;
        this.positonId = positonId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PositionDataId that = (PositionDataId) o;
        return Objects.equals(positonId, that.positonId) &&
                Objects.equals(empoyeeId, that.empoyeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positonId, empoyeeId);
    }
}
