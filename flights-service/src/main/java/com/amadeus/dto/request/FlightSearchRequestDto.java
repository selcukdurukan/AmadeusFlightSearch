package com.amadeus.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
        name = "Search request flight",
        description = "Schema to search flight"
)
public class FlightSearchRequestDto {

    @NotBlank(message = "Departure airport must not be blank.")
    @Schema(description = "Departure airport", example = "İstanbul Havalimanı")
    private String departureAirport;
    @Schema(description = "Arrival airport", example = "Gazipaşa Havalimanı")
    @NotBlank(message = "Arrival airport must not be blank.")
    private String arrivalAirport;
    @JsonFormat(pattern="dd/MM/yyyy")
    @Schema (description = "Departure date", type = "string", example = "01/01/2025")
    private Date departureDate;
    @JsonFormat(pattern="dd/MM/yyyy")
    @Schema (description = "Return date", type = "string", example = "05/01/2025")
    private Date returnDate;
}
