package com.kowalczyk.hurtownia.model.entities.wholesalers;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final String nameOfCategory;

    @OneToMany()
    private List<Product> products;

    public Category(final String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }


    @Override
    public String toString()
    {
        return id+" - "+nameOfCategory;
    }


}
