package com.kowalczyk.hurtownia.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private final String nameOfProduct;

    @Column
    private final String brand;

    @Column
    private final Double pricePerItem;

    @Column
    private final String productCode;

    @ManyToOne()
    private Category category;

    //@Column(name = "category", insertable = false, updatable = false)
    //private final Long categoryId;


    public Product(String nameOfProduct, String brand, Double pricePerItem, String productCode, Category category) {
        this.nameOfProduct = nameOfProduct;
        this.brand = brand;
        this.pricePerItem = pricePerItem;
        this.productCode = productCode;
        this.category = category;
    }
}
