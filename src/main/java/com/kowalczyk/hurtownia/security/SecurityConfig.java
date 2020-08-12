package com.kowalczyk.hurtownia.security;


import com.kowalczyk.hurtownia.model.services.employees.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAccountService userAccountService;

    private String[] endPointForAdminPostMethod = new String[]{
            "/api/employee/employee",
            "/api/employee/userAccount",
            "/apiemployee/position",
            "/api/position",
            "/api/category",
            "/api/product",
            "/api/wholesale"

    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                    .authorizeRequests() //Role Admin
                        .antMatchers(HttpMethod.PATCH)
                            .access("hasRole('ROLE_ADMIN')")
                        .antMatchers(HttpMethod.PUT)
                            .access("hasRole('ROLE_ADMIN')")
                        .antMatchers(HttpMethod.DELETE)
                            .access("hasRole('ROLE_ADMIN')")
                        .antMatchers(HttpMethod.POST,endPointForAdminPostMethod)
                            .access("hasRole('ROLE_ADMIN')")
                        .antMatchers(HttpMethod.GET,"/api/wholesale")
                            .access("hasRole('ROLE_ADMIN')")
                        //Role User
                        .antMatchers(HttpMethod.GET,"/api/employee")
                            .access("hasRole('ROLE_USER')")
                        // another
                        .antMatchers("/**")
                            .access("permitAll")
                .and()
                    .formLogin()
                .and()
                    .logout()
                        .logoutSuccessUrl("/login")
                .and()
                    .csrf()
                        .disable()
                            .authorizeRequests()
                                .anyRequest()
                                    .permitAll()
                .and()
                    .headers()
                        .frameOptions()
                            .sameOrigin();

    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
        //you can create your own password Encoder or delete this
        //and class MyPasswordEncoder
        //return new MyPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    private void createFirstAdminAccountIfNotExist()
    {
        if (!userAccountService.ifAtLeastOneUserExists())
        {
            userAccountService.createFirstAdminAccount(encoder());
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userAccountService)
                .passwordEncoder(encoder());

    }

}