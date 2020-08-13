package com.kowalczyk.hurtownia.model.resourceAssembler;

import com.kowalczyk.hurtownia.controller.wholesalers.CategoryController;
import com.kowalczyk.hurtownia.controller.wholesalers.ProductController;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Category;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.CategoryRepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class CategoryRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<Category, CategoryRepresentationModel> {

    private final String path;
    private List<Product> products;


    public CategoryRepresentationModelAssembler(String path) {
        super(CategoryController.class, CategoryRepresentationModel.class);
        this.path=path;
        this.products = new ArrayList<Product>();
    }

    @Override
    public CategoryRepresentationModel toModel(Category entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected CategoryRepresentationModel instantiateModel(
            Category entity) {
        return new CategoryRepresentationModel(entity,products);
    }

    protected CategoryRepresentationModel createModelWithId(Long id, Category entity) {

        Assert.notNull(entity, "Entity must not be null!");
        Assert.notNull(id, "Id must not be null!");

        CategoryRepresentationModel instance = instantiateModel(entity);
        instance.add(linkTo(CategoryController.class).slash(path).slash("name").
                slash(entity.getNameOfCategory().replace(" ","_")).withSelfRel());
        return instance;
    }

    public void setProducts(List<Product> products)
    {
        this.products=products;
    }
}
