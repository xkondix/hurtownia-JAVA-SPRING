package com.kowalczyk.hurtownia.model.entities.client;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
public class OrderSupply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private final TypeOfService typeOfService;
    private final Long wholesale;
    @ManyToOne()
    @JoinColumn(name = "client_id")
    private final Client client;

    @OneToMany(
            mappedBy = "orderSupply",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderSupllyWholesaleProduct> products = new ArrayList<>();

    public OrderSupply(TypeOfService typeOfService, Long wholesale, Client client) {
        this.typeOfService = typeOfService;
        this.wholesale = wholesale;
        this.client = client;
    }

    public OrderSupply(TypeOfService typeOfService, Client client) {
        this.typeOfService = typeOfService;
        this.client = client;
        this.wholesale = null;
    }

    @PrePersist
    void placedAt() {
        System.out.println("xd");
        this.createdAt = new Date();
    }


    public static enum TypeOfService {
        ORDER, SUPPLY
    }
}
