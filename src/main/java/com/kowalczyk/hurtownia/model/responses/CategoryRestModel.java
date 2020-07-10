package com.kowalczyk.hurtownia.model.responses;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CategoryRestModel  {

    private final String nameOfCategory;


    public CategoryRestModel(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }
}
