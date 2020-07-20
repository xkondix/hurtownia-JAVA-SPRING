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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    private final String name;
    private final String surename;

    @OneToOne()
    @JoinColumn(name = "id")
    private UserAccount userAccount;

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PositionData> tags = new ArrayList<>();


    public Employee(String name, String surename, UserAccount userAccount)
    {
        this.surename=surename;
        this.name = name;
        this.userAccount = userAccount;
    }

}
