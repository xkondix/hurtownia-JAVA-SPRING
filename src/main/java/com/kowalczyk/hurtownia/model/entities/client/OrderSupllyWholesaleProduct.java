package com.kowalczyk.hurtownia.model.entities.client;

import com.kowalczyk.hurtownia.model.entities.wholesalers.WholesaleProduct;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private final WholesaleProduct wholesaleProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderSupplyId")
    private final OrderSupply orderSupply;


    public OrderSupllyWholesaleProduct(Long quantity, WholesaleProduct wholesaleProduct, OrderSupply orderSupply) {
        this.quantity = quantity;
        this.wholesaleProduct = wholesaleProduct;
        this.orderSupply = orderSupply;
        this.typeOfService = this.orderSupply.getTypeOfService();
    }


}
