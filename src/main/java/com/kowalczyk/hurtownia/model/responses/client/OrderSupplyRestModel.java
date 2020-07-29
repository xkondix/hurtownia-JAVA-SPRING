package com.kowalczyk.hurtownia.model.responses.client;

import com.kowalczyk.hurtownia.model.entities.client.OrderSupply.TypeOfService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrderSupplyRestModel {

    private final Map<String,Long> productsCount;
    private final TypeOfService type;
    private final Long wholesaleId;
    private final Long clientid;

    public OrderSupplyRestModel(Map<String, Long> productsCount, String type, Long wholesaleId, Long clientid) {
        this.productsCount = productsCount;
        this.type = TypeOfService.valueOf(type);
        this.wholesaleId = wholesaleId;
        this.clientid = clientid;
    }

    public OrderSupplyRestModel(Map<String, Long> productsCount, String type, Long clientid) {
        this.productsCount = productsCount;
        this.type = TypeOfService.valueOf(type);
        this.clientid = clientid;
        this.wholesaleId = null;
    }



}

