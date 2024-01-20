package com.amadeus.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Schema(
        name = "Error response",
        description = "Schema to hold the error response information"
)
public class ErrorMessage {
    @Schema(description = "Error code representing the error happened")
    private int code;
    @Schema(description = "Error message representing the error happened")
    private String message;
    @Schema(description = "Detailed error fields")
    private List<String> fields;

}
