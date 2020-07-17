package com.kowalczyk.hurtownia.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


    public Employee(String name, String surename, UserAccount userAccount)
    {
        this.surename=surename;
        this.name = name;
        this.userAccount = userAccount;
    }

}
