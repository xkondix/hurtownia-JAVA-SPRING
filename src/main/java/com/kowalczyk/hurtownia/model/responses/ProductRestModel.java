package com.kowalczyk.hurtownia.model.responses;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ProductRestModel {

    private final String nameOfProduct;
    private final String brand;
    private final Double pricePerItem;
    private final String productCode;
    private final Long idCategory;


    public ProductRestModel(String nameOfProduct, String brand, Double pricePerItem, String productCode, Long idCategory) {
        this.nameOfProduct = nameOfProduct;
        this.brand = brand;
        this.pricePerItem = pricePerItem;
        this.productCode = productCode;
        this.idCategory = idCategory;
    }
}
