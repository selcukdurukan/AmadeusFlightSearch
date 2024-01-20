package com.amadeus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
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
        name = "Update request flight",
        description = "Schema to update with flight information"
)
public class UpdateFlightRequestDto {
    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIn0.dozjgNryP4J3jVmNHl0w5N_XgL0n3I9PlFUP0THsR8U")
    private String token;
    @Size(min = 24, max = 24, message = "ID must be 24 characters, not less or not more.")
    @Schema(description = "Flight ID", example = "65a99ec75507d841d5f805b2")
    private String id;
    @Schema(description = "Departure airport", example = "İstanbul Havalimanı")
    private String departureAirport;
    @Schema(description = "Arrival airport", example = "Gazipaşa Havalimanı")
    private String arrivalAirport;
    @Schema (description = "Departure time", type = "string", example = "01/01/2025 - 20:00")
    private Date departureTime;
    @Schema (description = "Arrival time", type = "string", example = "01/01/2025 - 21:00")
    private Date arrivalTime;
    @Schema(description = "Ticket price", example = "10000")
    private Double price;
}
