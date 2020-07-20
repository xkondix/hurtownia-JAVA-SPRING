package com.kowalczyk.hurtownia.model.entities.employees;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Position {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final String nameOfPosition;

    public Position(String nameOfPosition) {
        this.nameOfPosition = nameOfPosition;
    }



    @OneToMany(
            mappedBy = "position",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PositionData> posts = new ArrayList<>();
}
