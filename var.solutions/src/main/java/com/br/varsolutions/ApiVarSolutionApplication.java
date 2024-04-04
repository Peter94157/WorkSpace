package com.br.varsolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"com.br.varsolutions.domain.entities"})
@EnableJpaRepositories(basePackages = {"com.br.varsolutions.domain.repositories"})
@ComponentScan(basePackages = {"com.br.varsolutions"})
@SpringBootApplication
public class ApiVarSolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVarSolutionApplication.class, args);
	}

}
