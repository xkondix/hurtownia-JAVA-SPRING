package com.kowalczyk.hurtownia.model.entities.employees;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final Double reward;

    public Reward(Double reward)
    {
        this.reward=reward;
    }

}
