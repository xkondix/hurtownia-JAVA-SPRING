package com.kowalczyk.hurtownia.model.resourceAssembler;

import com.kowalczyk.hurtownia.controller.wholesalers.ProductController;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductQuantityRepresentationModel;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductRepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.util.Assert;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ProductQuantityRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductQuantityRepresentationModel> {

    private final Long quantity;
    private final String path;


    public ProductQuantityRepresentationModelAssembler(String path, Long quantity) {
        super(ProductController.class, ProductQuantityRepresentationModel.class);
        this.quantity = quantity;
        this.path = path;
    }




    @Override
    public ProductQuantityRepresentationModel toModel(Product entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected ProductQuantityRepresentationModel instantiateModel(
            Product entity) {
        return new ProductQuantityRepresentationModel(entity,quantity);
    }

    protected ProductQuantityRepresentationModel createModelWithId(Long id, Product entity) {

        Assert.notNull(entity, "Entity must not be null!");
        Assert.notNull(id, "Id must not be null!");

        ProductQuantityRepresentationModel instance = instantiateModel(entity);
        instance.add(linkTo(ProductController.class).slash(path).slash(id).withSelfRel());
        return instance;
    }


}
