package com.kowalczyk.hurtownia.model.entities.wholesalers;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
public class Wholesale {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final String nameOfWholesale;


    @OneToMany(
            //mappedBy = "wholesale",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(
            mappedBy = "wholesale",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WholesaleProduct> products = new ArrayList<>();

    public Wholesale(String nameOfWholesale) {
        this.nameOfWholesale = nameOfWholesale;
    }
}
