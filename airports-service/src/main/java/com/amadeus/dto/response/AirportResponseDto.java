package com.amadeus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Airport",
        description = "Schema to hold airport information"
)
public class AirportResponseDto {
    @Schema(description = "Airport ID", example = "10")
    private Long airportId;
    @Schema(description = "City", example = "İstanbul")
    private String city;
    @Schema(description = "Airport name", example = "Atatürk Havalimanı")
    private String airportName;
}
