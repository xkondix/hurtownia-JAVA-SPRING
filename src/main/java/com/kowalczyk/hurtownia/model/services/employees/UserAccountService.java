package com.kowalczyk.hurtownia.model.services.employees;

import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.repositories.employees.UserAccountRepository;
import com.kowalczyk.hurtownia.model.responses.employees.UserAccountRestModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository userAccountRespository;

    public UserAccountService(UserAccountRepository userAccountRespository) {
        this.userAccountRespository = userAccountRespository;
    }


    public void patchUser(UserAccountRestModel userAccountRestModel, String username, PasswordEncoder passwordEncoder) {

        UserAccount userAccount = userAccountRespository.findByUsername(username);
        if(userAccountRestModel.getPassword()!=null)
        {
            userAccount.setPassword(passwordEncoder
                    .encode(userAccountRestModel.getPassword()));
        }
        if(userAccountRestModel.getRoles()!=null)
        {
            userAccount.setAuthorities(userAccountRestModel.getRoles());
        }
        if(userAccountRestModel.getActive()!=null)
        {
            userAccount.setActive(userAccountRestModel.getActive());
        }
        userAccountRespository.save(userAccount);
    }

    public void putUser(UserAccountRestModel userAccountRestModel, String username, PasswordEncoder passwordEncoder) {
    UserAccount userAccount = userAccountRestModel.mapToEntity(passwordEncoder);
    userAccount.setId(userAccountRespository.findByUsername(username).getId());
    userAccountRespository.save(userAccount);
    }

    public void saveUser(UserAccountRestModel userAccountRestModel, PasswordEncoder passwordEncoder) {
        userAccountRespository.save(userAccountRestModel.mapToEntity(passwordEncoder));
    }

    public Boolean ifAtLeastOneUserExists() {
        //TODO ifAnyoneExist, this method will work very slowly with a large users
        return userAccountRespository.findAll().size()>0;
    }

    public void createFirstAdminAccount(PasswordEncoder encoder)
    {
        userAccountRespository.save(new UserAccount(
                "admin", encoder.encode("1234"),true,"ROLE_ADMIN,ROLE_USER"));
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
