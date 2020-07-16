package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.UserAccount;
import com.kowalczyk.hurtownia.model.repositories.UserAccountRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountRespository userAccountRespository;

    public UserAccountService(UserAccountRespository userAccountRespository) {
        this.userAccountRespository = userAccountRespository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserAccount user = userAccountRespository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }


}
