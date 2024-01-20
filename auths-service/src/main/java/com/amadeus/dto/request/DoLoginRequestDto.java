package com.amadeus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
        name = "Login request",
        description = "Schema to request for login"
)
public class DoLoginRequestDto {

    @Email(message = "Email must be valid.")
    @Schema(description = "E-mail", example = "mailmail@gmail.com")
    private String email;
    @Size(min = 8, max = 32, message = "Character size must be between 8 and 32.")
    @Schema(description = "Password")
    private String password;


}
