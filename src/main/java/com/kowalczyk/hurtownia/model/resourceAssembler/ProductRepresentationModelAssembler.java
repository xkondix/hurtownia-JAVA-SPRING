package com.kowalczyk.hurtownia.model.resourceAssembler;
import com.kowalczyk.hurtownia.controller.wholesalers.ProductController;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductRepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.util.Assert;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ProductRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<Product, ProductRepresentationModel> {

    private String path;


    public ProductRepresentationModelAssembler(String path) {
        super(ProductController.class, ProductRepresentationModel.class);
        this.path=path;
    }

    @Override
    public ProductRepresentationModel toModel(Product entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected ProductRepresentationModel instantiateModel(
            Product entity) {
        return new ProductRepresentationModel(entity);
    }

    protected ProductRepresentationModel createModelWithId(Long id, Product entity) {

        Assert.notNull(entity, "Entity must not be null!");
        Assert.notNull(id, "Id must not be null!");

        ProductRepresentationModel instance = instantiateModel(entity);
        instance.add(linkTo(ProductController.class).slash(path).slash(id).withSelfRel());
        return instance;
    }
}
