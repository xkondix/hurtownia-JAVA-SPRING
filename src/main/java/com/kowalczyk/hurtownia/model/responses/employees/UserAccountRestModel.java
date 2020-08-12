package com.kowalczyk.hurtownia.model.responses.employees;

import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserAccountRestModel {

    private final String username;
    private final String password;
    private final Boolean active;
    private final String roles;

    public UserAccountRestModel(String username, String password, boolean active, String roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public UserAccount mapToEntity(PasswordEncoder passwordEncoder)
    {
        return new UserAccount(username, passwordEncoder.encode(password), active, roles);
    }
}
