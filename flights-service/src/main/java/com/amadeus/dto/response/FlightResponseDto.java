package com.amadeus.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
        name = "Fight response",
        description = "Schema to response flight information"
)
public class FlightResponseDto {
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
    private double price;
}
