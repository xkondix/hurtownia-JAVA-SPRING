package com.kowalczyk.hurtownia.model.representationModel.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

public class WholesaleRepresentationModel {

    @Getter
    private final String nameOfWholesale;
    @Getter
    private final List<List<String>> products;
    @Getter
    private final List<String> employees;

    public WholesaleRepresentationModel(Wholesale wholesale, List<String> employees) {
        this.nameOfWholesale=wholesale.getNameOfWholesale();
        this.products = wholesale.getProducts().stream()
                .map(product -> product.getListForWholesale())
                .collect(Collectors.toList());
        this.employees = employees;
    }
}
