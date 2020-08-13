package com.kowalczyk.hurtownia.model.representationModel.wholesalers;

import com.kowalczyk.hurtownia.controller.wholesalers.CategoryController;
import com.kowalczyk.hurtownia.controller.wholesalers.ProductController;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.resourceAssembler.CategoryRepresentationModelAssembler;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Relation(value="product", collectionRelation="products")
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
    private String category;

    public ProductRepresentationModel(Product entity) {
        this.nameOfProduct = entity.getNameOfProduct();
        this.brand = entity.getBrand();
        this.pricePerItem = entity.getPricePerItem();
        this.productCode = entity.getProductCode();

        this.category = entity.getCategory().getNameOfCategory();
        this.add(linkTo(CategoryController.class).slash("category").slash("name").
                slash(category).withSelfRel());

    }
}
