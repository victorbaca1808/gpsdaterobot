package com.peru.combi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.peru.combi.repository")
public class CombiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CombiApplication.class, args);
	}

}
