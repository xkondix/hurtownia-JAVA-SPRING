package com.kowalczyk.hurtownia.model.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_product")
    private final Long idProduct;

    @Column(name = "name_of_product")
    private final String nameOfProduct;

    @Column(name = "brand")
    private final String brand;

    @Column(name = "price_per_item")
    private final Double pricePerItem;

    @Column(name = "product_code")
    private final String productCode;

    @Column(name = "id_category")
    private final Long idCategory;


}
