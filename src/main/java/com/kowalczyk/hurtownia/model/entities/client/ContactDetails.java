package com.kowalczyk.hurtownia.model.entities.client;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ContactDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "(\\+48|0)[0-9]{9}")
    private final String phoneNumber;
    @Email(message = "Wrong email")
    private final String email;

    @OneToOne(mappedBy = "contactDetails")
    private Client client;


    public ContactDetails(String phoneNumber, String email)
    {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
