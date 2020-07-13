package com.kowalczyk.hurtownia.model.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kowalczyk.hurtownia.model.entities.Category;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import java.util.Set;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CategoryRestModel  {

    private final String nameOfCategory;
    @JsonIgnore
    private final Set<ProductRestModel> products;
    private Set<EntityModel<ProductRestModel>> products2;
    @JsonIgnore
    private final Long id;


    public CategoryRestModel(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
        this.products = null;
        this.id = null;
    }

    public CategoryRestModel(Category category, Set<ProductRestModel> products ) {
        this.nameOfCategory = category.getNameOfCategory();
        this.products = products;
        this.id = category.getId();
    }




}
