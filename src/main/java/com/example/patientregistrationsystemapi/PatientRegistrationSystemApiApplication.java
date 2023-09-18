package com.example.patientregistrationsystemapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class PatientRegistrationSystemApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PatientRegistrationSystemApiApplication.class, args);
	}
}
