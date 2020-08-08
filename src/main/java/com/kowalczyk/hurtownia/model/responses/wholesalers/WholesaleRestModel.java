package com.kowalczyk.hurtownia.model.responses.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Wholesale;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class WholesaleRestModel {


    private final String nameOfWholesale;


    public WholesaleRestModel(String nameOfWholesale) {
        this.nameOfWholesale = nameOfWholesale;
    }

    public Wholesale mapToEnity()
    {
        return new Wholesale(nameOfWholesale);
    }
}
