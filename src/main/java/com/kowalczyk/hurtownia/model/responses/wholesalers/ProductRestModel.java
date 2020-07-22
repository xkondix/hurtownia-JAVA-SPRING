package com.kowalczyk.hurtownia.model.responses.wholesalers;

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
    private final Long categoryId;



    public ProductRestModel(String nameOfProduct, String brand, Double pricePerItem, String productCode, Long categoryId) {
        this.nameOfProduct = nameOfProduct;
        this.brand = brand;
        this.pricePerItem = pricePerItem;
        this.productCode = productCode;
        this.categoryId = categoryId;
    }


    @Override
    public String toString()
    {
        return categoryId+" "+nameOfProduct+" "+brand+" "+pricePerItem+" "+productCode;
    }

}
