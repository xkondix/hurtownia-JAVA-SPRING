package com.kowalczyk.hurtownia.model.responses;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ProductRestModel {

    private final String nameOfProduct;
    private final String brand;
    private final Double pricePerItem;
    private final String productCode;
    private final Long idCategory;
}
