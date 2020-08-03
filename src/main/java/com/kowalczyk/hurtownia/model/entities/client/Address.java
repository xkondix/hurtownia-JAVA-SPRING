package com.kowalczyk.hurtownia.model.entities.client;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Delivery building number is required")
    private final String deliveryBuildingNumber;

    @NotBlank(message="Street is required")
    private final String deliveryStreet;

    @NotBlank(message="City is required")
    private final String deliveryCity;

    @NotBlank(message="State is required")
    private final String deliveryState;

    @NotBlank(message="Zip code is required")
    private final String deliveryZip;

    @OneToOne(mappedBy = "address")
    private Client client;

    public Address(String deliveryBuildingNumber,String deliveryStreet,
                   String deliveryCity,String deliveryState,String deliveryZip) {
        this.deliveryBuildingNumber = deliveryBuildingNumber;
        this.deliveryStreet = deliveryStreet;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryZip = deliveryZip;
    }
}
