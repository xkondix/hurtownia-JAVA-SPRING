package com.kowalczyk.hurtownia.model.representationModel;

import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.resourceAssembler.CategoryRepresentationModelAssembler;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;


public class ProductRepresentationModel extends RepresentationModel<ProductRepresentationModel>
{
    @Getter
    private String nameOfProduct;

    @Getter
    private String brand;

    @Getter
    private  Double pricePerItem;

    @Getter
    private  String productCode;

    @Getter
    private CategoryRepresentationModel category;

    public ProductRepresentationModel(Product entity) {
        this.nameOfProduct = entity.getNameOfProduct();
        this.category = new CategoryRepresentationModelAssembler("category")
                .toModel(entity.getCategory());
        this.brand = entity.getBrand();
        this.pricePerItem = entity.getPricePerItem();
        this.productCode = entity.getProductCode();
    }
}
