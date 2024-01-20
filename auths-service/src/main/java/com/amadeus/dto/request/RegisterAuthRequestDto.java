package com.amadeus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
        name = "Register request",
        description = "Schema to request for register"
)
public class RegisterAuthRequestDto {

    @Email(message = "Email must be valid")
    @Schema(description = "E-mail", example = "mailmail@gmail.com")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Schema(description = "Password")
    @Size(min = 8, max = 32, message = "Character size must be between 8 and 32.")
    private String password;
    @NotBlank(message = "Retry-password cannot be empty")
    @Schema(description = "Repassword")
    @Size(min = 8, max = 32, message = "Character size must be between 8 and 32.")
    private String rePassword;

}
