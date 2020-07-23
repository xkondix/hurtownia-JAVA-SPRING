package com.kowalczyk.hurtownia.model.entities.wholesalers;


import com.kowalczyk.hurtownia.model.entities.client.OrderSupllyWholesaleProduct;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
public class WholesaleProduct {


    @EmbeddedId
    private WholesaleProductId id;
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private final Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("wholesaleId")
    private final Wholesale wholesale;

    @OneToMany(
            mappedBy = "wholesaleProduct",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderSupllyWholesaleProduct> orders = new ArrayList<>();

    public WholesaleProduct(Long quantity, Product product, Wholesale wholesale) {
        this.quantity = quantity;
        this.product = product;
        this.wholesale = wholesale;
    }

    public void addProduct(Long quantity)
    {
        this.quantity+=quantity;
    }

    public void subtractProduct(Long quantity)
    {
        this.quantity-=quantity;
    }

    public List<String> getListForWholesale()
    {
        return new ArrayList<String>(Arrays.asList(
                "Product : "+product.getNameOfProduct()
                ,"Quantity : "+quantity));
    }

    public List<String> getListForProduct()
    {
        return new ArrayList<String>(Arrays.asList(
                "Wholesale : "+wholesale.getNameOfWholesale()
                ,"Quantity : "+quantity));

    }


    public String toString()
    {
        return "WholesaleProduct";
    }


}
