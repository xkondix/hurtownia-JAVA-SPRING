package com.kowalczyk.hurtownia.model.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kowalczyk.hurtownia.model.entities.Product;
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
    @JsonIgnore
    private final Long categoryId;


    public ProductRestModel(String nameOfProduct, String brand, Double pricePerItem, String productCode, Long categoryId) {
        this.nameOfProduct = nameOfProduct;
        this.brand = brand;
        this.pricePerItem = pricePerItem;
        this.productCode = productCode;
        this.categoryId = categoryId;
    }

    public ProductRestModel(String nameOfProduct, String brand, Double pricePerItem, String productCode) {
        this.nameOfProduct = nameOfProduct;
        this.brand = brand;
        this.pricePerItem = pricePerItem;
        this.productCode = productCode;
        this.categoryId = null;
    }

    public ProductRestModel(Product product) {

        this.nameOfProduct = product.getNameOfProduct();
        this.brand = product.getBrand();
        this.pricePerItem = product.getPricePerItem();
        this.productCode = product.getNameOfProduct();
        this.categoryId = product.getCategory().getId();

    }

    @Override
    public String toString()
    {
        return categoryId+" "+nameOfProduct+" "+brand+" "+pricePerItem+" "+productCode;
    }

}
