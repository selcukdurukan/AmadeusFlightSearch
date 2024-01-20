package com.amadeus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Update request airport",
        description = "Schema to update airport"
)
public class UpdateAirportRequestDto {

    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIn0.dozjgNryP4J3jVmNHl0w5N_XgL0n3I9PlFUP0THsR8U")
    private String token;
    @Schema(description = "Airport ID", example = "10")
    @Positive(message = "airportId must be positive and not be empty.")
    private long airportId;
    @Schema(description = "City", example = "İstanbul")
    private String city;
    @Schema(description = "Airport name", example = "İstanbul Havalimanı")
    private String airportName;

}