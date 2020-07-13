package com.kowalczyk.hurtownia.model.responses;

import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.entities.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CategoryRestModel  {

    private final String nameOfCategory;
    private final Set<ProductRestModel> products;


    public CategoryRestModel(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
        this.products = null;
    }

    public CategoryRestModel(Category category, Set<ProductRestModel> products ) {
        this.nameOfCategory = category.getNameOfCategory();
        this.products = products;
    }


}
