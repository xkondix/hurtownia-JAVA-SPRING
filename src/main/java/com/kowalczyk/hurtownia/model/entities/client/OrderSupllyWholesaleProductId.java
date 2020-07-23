package com.kowalczyk.hurtownia.model.entities.client;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrderSupllyWholesaleProductId implements Serializable {


    @Column(name = "product_id")
    private final Long productId;

    @Column(name = "order_supply_id")
    private final Long orderSupplyId;

    public OrderSupllyWholesaleProductId(Long productId, Long orderSupplyId) {
        this.productId = productId;
        this.orderSupplyId = orderSupplyId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderSupllyWholesaleProductId that = (OrderSupllyWholesaleProductId) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(orderSupplyId, that.orderSupplyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderSupplyId);
    }
}



