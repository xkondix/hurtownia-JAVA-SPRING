package com.kowalczyk.hurtownia;

import com.kowalczyk.hurtownia.model.repositories.UserAccountRespository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserAccountRespository.class)
public class HurtowniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HurtowniaApplication.class, args);
	}

}
