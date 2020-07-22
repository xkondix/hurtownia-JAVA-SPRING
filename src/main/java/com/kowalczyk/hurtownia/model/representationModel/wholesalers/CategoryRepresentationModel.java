package com.kowalczyk.hurtownia.model.representationModel.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Category;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.resourceAssembler.ProductRepresentationModelAssembler;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


import java.util.List;
import java.util.stream.Collectors;

@Relation(value="category", collectionRelation="categories")
public class CategoryRepresentationModel extends RepresentationModel<CategoryRepresentationModel> {

    private static final ProductRepresentationModelAssembler
            modelAssembler = new ProductRepresentationModelAssembler("product");


    @Getter
    private String nameOfCategory;

    @Getter
    private List<ProductRepresentationModel> products;

    public  CategoryRepresentationModel(Category category, List<Product> products)
    {
        this.nameOfCategory = category.getNameOfCategory();
        this.products = products.stream()
                .map(x -> new ProductRepresentationModelAssembler("product").toModel(x)).collect(Collectors.toList());
    }

}
