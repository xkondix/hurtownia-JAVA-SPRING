package com.kowalczyk.hurtownia.model.representationModel.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Relation(value="productQuantity", collectionRelation="products")
public class ProductQuantityRepresentationModel extends ProductRepresentationModel {

    @Getter
    private final Long quantity;

    public ProductQuantityRepresentationModel(Product entity, Long quantity) {
        super(entity);
        this.quantity = quantity;
    }
}
