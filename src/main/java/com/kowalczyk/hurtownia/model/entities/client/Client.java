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

    public Client(TypeOfClient typeOfClient) {
        this.typeOfClient = typeOfClient;
    }


    public static enum TypeOfClient {
        COMPANY, PERSON
    }
}
