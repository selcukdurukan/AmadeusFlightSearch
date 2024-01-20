package com.amadeus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Create request flight",
        description = "Schema to create with flight information"
)
public class CreateFlightRequestDto {

    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIn0.dozjgNryP4J3jVmNHl0w5N_XgL0n3I9PlFUP0THsR8U")
    private String token;
    @Schema(description = "Departure airport", example = "İstanbul Havalimanı")
    @NotBlank(message = "Name of the departure airport mustn't be null or empty")
    private String departureAirport;
    @NotBlank(message = "Name of the arrival airport mustn't be null or empty")
    @Schema(description = "Arrival airport", example = "Gazipaşa Havalimanı")
    private String arrivalAirport;
    @Future(message = "Departure date must be in the future")
    @Schema (description = "Departure time", type = "string", example = "01/01/2025 - 20:00")
    private Date departureTime;
    @Schema (description = "Arrival time", type = "string", example = "01/01/2025 - 21:00")
    @Future(message = "Arrival date must be in the future")
    private Date arrivalTime;
    @Schema(description = "Ticket price", example = "10000")
    @Positive(message = "The ticket price must be greater than zero and not be null or empty")
    private double price;

}