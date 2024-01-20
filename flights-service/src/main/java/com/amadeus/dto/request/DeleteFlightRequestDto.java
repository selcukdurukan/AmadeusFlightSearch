package com.amadeus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Delete request flight",
        description = "Schema to delete flight with id"
)
public class DeleteFlightRequestDto {
    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIn0.dozjgNryP4J3jVmNHl0w5N_XgL0n3I9PlFUP0THsR8U")
    private String token;
    @Schema(description = "Flight ID", example = "65a99ec75507d841d5f805b2")
    @Size(min = 24, max = 24, message = "ID must be 24 characters, not less or not more.")
    private String id;

}
