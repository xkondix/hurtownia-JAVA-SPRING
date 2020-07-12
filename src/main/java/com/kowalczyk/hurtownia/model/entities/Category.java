package com.kowalczyk.hurtownia.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private final String nameOfCategory;

    @OneToMany()
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
