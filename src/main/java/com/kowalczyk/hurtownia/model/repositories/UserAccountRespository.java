package com.kowalczyk.hurtownia.model.repositories;

import com.kowalczyk.hurtownia.model.entities.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRespository extends JpaRepository<UserAccount,Long> {

    UserAccount findByUsername(String username);
}
