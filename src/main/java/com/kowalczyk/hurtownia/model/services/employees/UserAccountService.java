package com.kowalczyk.hurtownia.model.services.employees;

import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.repositories.employees.UserAccountRepository;
import com.kowalczyk.hurtownia.model.responses.employees.UserAccountRestModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository userAccountRespository;

    public UserAccountService(UserAccountRepository userAccountRespository) {
        this.userAccountRespository = userAccountRespository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        System.out.println(username);
        UserAccount user = userAccountRespository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }


    public void saveUser(UserAccountRestModel userAccountRestModel) {
        userAccountRespository.save(userAccountRestModel.mapToEntity());
    }
}
