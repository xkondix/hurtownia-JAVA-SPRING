package com.kowalczyk.hurtownia.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private final String nameOfCategory;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category(final String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    @Override
    public String toString()
    {
        return id+" - "+nameOfCategory;
    }


}
