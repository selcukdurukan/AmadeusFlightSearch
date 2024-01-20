package com.amadeus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


@OpenAPIDefinition(
        info = @Info(
                title = "Flights microservice REST API Documentation",
                description =  "Amedeus Flight Search Flights microservice REST API Documentation",
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
@EnableMongoAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class FlightServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlightServiceApplication.class, args);
    }
}
