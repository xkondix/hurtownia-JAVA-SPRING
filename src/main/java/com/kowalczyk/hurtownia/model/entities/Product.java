package com.kowalczyk.hurtownia.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "category_id")
    private Category category;


    public Product(String nameOfProduct, String brand, Double pricePerItem, String productCode, Category category) {
        this.nameOfProduct = nameOfProduct;
        this.brand = brand;
        this.pricePerItem = pricePerItem;
        this.productCode = productCode;
        this.category = category;
    }
}
