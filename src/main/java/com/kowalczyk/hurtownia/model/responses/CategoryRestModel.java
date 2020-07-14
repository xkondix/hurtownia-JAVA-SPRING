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


    public CategoryRestModel(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }


}
