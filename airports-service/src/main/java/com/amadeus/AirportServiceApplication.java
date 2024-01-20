package com.amadeus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "Airports microservice REST API Documentation",
                description =  "Amedeus Flight Search Airports microservice REST API Documentation",
                version = "v0.0.1",
                contact = @Contact(
                        name = "Selçuk Durukan",
                        email = "upfront-seek0b@icloud.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
public class AirportServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AirportServiceApplication.class, args);
    }
}