package com.kowalczyk.hurtownia.model.responses.client;

import com.kowalczyk.hurtownia.model.entities.client.Address;
import com.kowalczyk.hurtownia.model.entities.client.Client.TypeOfClient;
import com.kowalczyk.hurtownia.model.entities.client.ContactDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ClientRestModel {

    private final TypeOfClient typeOfClient;
    private final String phoneNumber;
    private final String email;
    private final String deliveryBuildingNumber;
    private final String deliveryStreet;
    private final String deliveryCity;
    private final String deliveryState;
    private final String deliveryZip;

    public ClientRestModel(String typeOfClient, String phoneNumber, String email, String deliveryBuildingNumber, String deliveryStreet, String deliveryCity, String deliveryState, String deliveryZip) {
        this.typeOfClient = TypeOfClient.valueOf(typeOfClient.toUpperCase());
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.deliveryBuildingNumber = deliveryBuildingNumber;
        this.deliveryStreet = deliveryStreet;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryZip = deliveryZip;
    }

    public Address mapToEnityAddres()
    {
        return new Address(deliveryBuildingNumber,deliveryStreet,
                deliveryCity,deliveryState,deliveryZip);
    }

    public ContactDetails mapToEnityContactDetails()
    {
        return new ContactDetails(phoneNumber,email);
    }
}
