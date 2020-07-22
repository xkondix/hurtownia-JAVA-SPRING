package com.kowalczyk.hurtownia.model.entities.employees;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final String name;
    private final String surename;

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<JobPositionEmployee> jobs = new ArrayList<>();

    @OneToOne()
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("wholesaleId")
    private Wholesale wholesale;


    public Employee(String name, String surename, UserAccount userAccount)
    {
        this.surename=surename;
        this.name = name;
        this.userAccount = userAccount;
    }

}
