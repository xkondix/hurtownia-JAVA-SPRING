package com.kowalczyk.hurtownia.model.entities.client;

import com.kowalczyk.hurtownia.model.entities.wholesalers.WholesaleProduct;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrderSupllyWholesaleProduct {

    @EmbeddedId
    private OrderSupllyWholesaleProductId id;
    private Long quantity;
    private final OrderSupply.TypeOfService typeOfService;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("wholesaleProductId")
    private WholesaleProduct wholesaleProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderSupplyId")
    private OrderSupply orderSupply;


    public OrderSupllyWholesaleProduct(Long quantity) {
        this.quantity = quantity;
        this.typeOfService = orderSupply.getTypeOfService();
    }


}
