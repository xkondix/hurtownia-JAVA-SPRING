package com.kowalczyk.hurtownia.model.entities.client;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
public class OrderSupply {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private final TypeOfService typeOfService;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(
            mappedBy = "orderSupply",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Size(min=1, message="You must choose at least 1 product")
    private List<OrderSupllyWholesaleProduct> products = new ArrayList<>();

    public OrderSupply(TypeOfService typeOfService, List<Long> wholesalersId) {
        this.typeOfService = typeOfService;
    }



    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }


    public static enum TypeOfService {
        ORDER, SUPPLY
    }
}
