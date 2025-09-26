package com.employee.back_gestionTempsDeTravail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionTempsDeTravailApplication {

	public static void main(String[] args) {
		SpringApplication.run( GestionTempsDeTravailApplication.class, args);
	}
}
