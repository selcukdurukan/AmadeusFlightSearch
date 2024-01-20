package com.amadeus.controller;

import com.amadeus.dto.request.CreateAirportRequestDto;
import com.amadeus.dto.request.DeleteAirportRequestDto;
import com.amadeus.dto.request.UpdateAirportRequestDto;
import com.amadeus.dto.response.AirportResponseDto;
import com.amadeus.dto.response.ResponseDto;
import com.amadeus.exception.ErrorMessage;
import com.amadeus.repository.entity.Airports;
import com.amadeus.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amadeus.constants.AirportsConstant.*;

@Tag(
        name = "CRUD REST APIs for Airports in Amadeus Flight Search",
        description = "CRUD REST APIs to CREATE, UPDATE, DELETE AND FETCH airports details"
)
@RestController
@RequestMapping(value = AIRPORTS, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AirportController {

    private final AirportService airportService;

    @Operation(
            summary = "Create Airport REST API",
            description = "REST API to create airport inside Amadeus Flight Search"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "1122",
                    description = "This airport have been already created",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @PostMapping(CREATE)
    public ResponseEntity<ResponseDto> createAirport(@RequestBody @Valid CreateAirportRequestDto dto) {
        airportService.createAirport(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Airport Details REST API",
            description = "REST API to fetch airport based on a long id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "1133",
                    description = "Airport could not find with giving id",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @GetMapping(FETCH)
    public ResponseEntity<AirportResponseDto> fetchAirport(@RequestParam("id")
                                                           @Positive(message = "ID must be positive number")
                                                           long id) {
        AirportResponseDto responseDto = airportService.fetchAirport(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(
            summary = "Fetch all airports Details REST API",
            description = "REST API to All fetch airports"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            )
    }
    )
    @GetMapping(FETCH_ALL)
    public ResponseEntity<List<Airports>> fetchAirport() {
        return ResponseEntity.status(HttpStatus.OK).body(airportService.findAll());
    }

    @Operation(
            summary = "Delete Airport REST API",
            description = "REST API to delete Airport based on long id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "1133",
                    description = "Airport could not find with giving id",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @DeleteMapping(DELETE)
    public ResponseEntity<ResponseDto> deleteAirport(@RequestBody @Valid DeleteAirportRequestDto dto) {
        airportService.deleteAirport(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(STATUS_200, MESSAGE_200));
    }

    @Operation(
            summary = "Update Airport Details REST API",
            description = "REST API to update Airport details based on long id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "1133",
                    description = "Airport could not find with giving id",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @PutMapping(UPDATE)
    public ResponseEntity<ResponseDto> updateAirport(@RequestBody @Valid UpdateAirportRequestDto dto) {
        boolean isUpdated = airportService.updateAirport(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(STATUS_200, MESSAGE_200));
    }

    @Operation(
            summary = "Check Airport whether have been or not",
            description = "REST API to check Airport based on String name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            )
    }
    )
    @GetMapping(HAS_FOUND_BY_NAME)
    public boolean hasFoundByAirportName(@PathVariable String name) {
        return airportService.fetchByAirportName(name);
    }
}
