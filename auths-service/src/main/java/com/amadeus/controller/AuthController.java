package com.amadeus.controller;

import com.amadeus.dto.request.DoLoginRequestDto;
import com.amadeus.dto.request.RegisterAuthRequestDto;
import com.amadeus.dto.response.DoLoginResponseDto;
import com.amadeus.dto.response.ResponseDto;
import com.amadeus.exception.ErrorMessage;
import com.amadeus.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static com.amadeus.constants.AuthsConstant.*;

@RestController
@RequestMapping(value = AUTH, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Register user REST API",
            description = "REST API to register new USER inside Amadeus Flight Search"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "3033",
                    description = "E-mail has already registered",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @PostMapping(REGISTER)
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterAuthRequestDto dto) {
        authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Login user and get JWT Token",
            description = "REST API to login new USER inside Amadeus Flight Search"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "3044",
                    description = "Email or password is incorrect",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @PostMapping(DO_LOGIN)
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(new DoLoginResponseDto(authService.doLogin(dto)));
    }
}
