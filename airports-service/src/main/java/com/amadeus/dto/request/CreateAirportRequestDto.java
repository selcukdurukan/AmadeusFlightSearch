package com.amadeus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Create request airport",
        description = "Schema to hold airport information"
)
public class CreateAirportRequestDto {

    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIn0.dozjgNryP4J3jVmNHl0w5N_XgL0n3I9PlFUP0THsR8U")
    private String token;
    @Schema(description = "City name", example = "İstanbul")
    @NotBlank(message = "You must give a name of the city.")
    private String city;
    @Schema(description = "Airport name", example = "Atatürk Havalimanı")
    @NotBlank(message = "You must give a name of the airport")
    private String airportName;

}