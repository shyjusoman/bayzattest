package com.mycom.platform.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

@ComponentScan("com.mycom.platform.*")
@EnableJpaRepositories("com.mycom.platform.*")
@EntityScan("com.mycom.platform.*")
public class MyHrApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyHrApplication.class, args);
	}
}
