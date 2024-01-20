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
@Schema(name = "Response", description = "Schema hold the response information")
public class ResponseDto {

    @Schema(description = "Status code", example = "200")
    private String statusCode;
    @Schema(description = "Status message", example = "Request processed successfully")
    private String statusMsg;
}