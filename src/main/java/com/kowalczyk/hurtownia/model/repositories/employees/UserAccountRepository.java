package com.kowalczyk.hurtownia.model.repositories.employees;

import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    UserAccount findByUsername(String username);

}
