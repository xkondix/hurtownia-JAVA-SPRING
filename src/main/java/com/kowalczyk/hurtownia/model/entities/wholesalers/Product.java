package com.kowalczyk.hurtownia.model.entities.wholesalers;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final String nameOfProduct;
    private final String brand;
    private final Double pricePerItem;
    private final String productCode;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WholesaleProduct> wholesalers = new ArrayList<>();


    public Product(String nameOfProduct, String brand, Double pricePerItem, String productCode, Category category) {
        this.nameOfProduct = nameOfProduct;
        this.brand = brand;
        this.pricePerItem = pricePerItem;
        this.productCode = productCode;
        this.category = category;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name of product : ");
        stringBuilder.append(nameOfProduct);
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("Brand : ");
        stringBuilder.append(brand);
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("Product Code : ");
        stringBuilder.append(productCode);
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("Category : ");
        stringBuilder.append(category.getNameOfCategory());
        stringBuilder.append(System.getProperty("line.separator"));
        return stringBuilder.toString();
    }
}
