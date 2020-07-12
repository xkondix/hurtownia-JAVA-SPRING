package com.kowalczyk.hurtownia.model.responses;

import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.entities.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.util.Set;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CategoryRestModel  {

    private final String nameOfCategory;
    private final Set<Product> products;


    public CategoryRestModel(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
        this.products = null;
    }

    public CategoryRestModel(Category category, Set<Product> products ) {
        this.nameOfCategory = category.getNameOfCategory();
        this.products = products;
    }


}
