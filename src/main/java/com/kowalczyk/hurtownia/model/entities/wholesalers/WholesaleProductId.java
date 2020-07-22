package com.kowalczyk.hurtownia.model.entities.wholesalers;

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
public class WholesaleProductId implements Serializable {


    @Column(name = "product_id")
    private final Long productId;

    @Column(name = "wholesale_id")
    private final Long wholesaleId;

    public WholesaleProductId(Long productId, Long wholesaleId) {
        this.productId = productId;
        this.wholesaleId = wholesaleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        WholesaleProductId that = (WholesaleProductId) o;
        return Objects.equals(wholesaleId, that.wholesaleId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, wholesaleId);
    }
}
