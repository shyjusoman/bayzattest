package com.bayzat.platform.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

@ComponentScan("com.bayzat.platform.*")
@EnableJpaRepositories("com.bayzat.platform.*")
@EntityScan("com.bayzat.platform.*")
public class BayzatHRApplication {
	public static void main(String[] args) {
		SpringApplication.run(BayzatHRApplication.class, args);
	}
}
