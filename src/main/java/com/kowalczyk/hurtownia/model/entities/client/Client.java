package com.kowalczyk.hurtownia.model.entities.client;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private final TypeOfClient typeOfClient;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderSupply> orders = new ArrayList<>();

    @OneToOne()
    @JoinColumn(name = "address_id")
    private final Address address;

    @OneToOne()
    @JoinColumn(name = "contact_details_id")
    private final ContactDetails contactDetails;

    public Client(TypeOfClient typeOfClient, Address address, ContactDetails contactDetails) {
        this.typeOfClient = typeOfClient;
        this.address = address;
        this.contactDetails = contactDetails;
    }


    public static enum TypeOfClient {
        COMPANY, PERSON
    }
}
