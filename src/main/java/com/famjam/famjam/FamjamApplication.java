package com.famjam.famjam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.famjam.famjam")
@EntityScan("com.famjam.famjam.entity")
@EnableJpaRepositories("com.famjam.famjam.repository")
public class FamjamApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamjamApplication.class, args);
	}

}
