package com.kowalczyk.hurtownia.model.entities.wholesalers;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class WholesaleProduct {


    @EmbeddedId
    private WholesaleProductId id;
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("wholesaleId")
    private Wholesale wholesale;

    public WholesaleProduct(Long quantity) {
        this.quantity = quantity;
    }

    public void addProduct(Long quantity)
    {
        this.quantity+=quantity;
    }

    public void subtractProduct(Long quantity)
    {
        this.quantity-=quantity;
    }

    public String toString()
    {
        return "WholesaleProduct";
    }


}
