package com.amadeus.controller;

import com.amadeus.dto.request.CreateFlightRequestDto;
import com.amadeus.dto.request.DeleteFlightRequestDto;
import com.amadeus.dto.request.FlightSearchRequestDto;
import com.amadeus.dto.request.UpdateFlightRequestDto;
import com.amadeus.dto.response.FlightResponseDto;
import com.amadeus.dto.response.ResponseDto;
import com.amadeus.exception.ErrorMessage;
import com.amadeus.repository.entity.Flights;
import com.amadeus.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amadeus.constants.FlightsConstant.*;

@RestController
@RequestMapping(value = FLIGHTS, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class FlightController {

    private final FlightService flightService;

    @Operation(
            summary = "Create new flight REST API",
            description = "REST API to create flight inside Amadeus Flight Search"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "1222",
                    description = "Given airports name cannot find. Check names",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @PostMapping(CREATE)
    public ResponseEntity<ResponseDto> createFlight(@RequestBody @Valid CreateFlightRequestDto dto) {
        flightService.createFlight(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Flight Details REST API",
            description = "REST API to fetch flight based on a String id"
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
    public ResponseEntity<FlightResponseDto> fetchById(@RequestParam @Size(min = 24, max = 24, message = "ID must be 24 characters, not less or not more.") String id) {
        FlightResponseDto flightResponseDto = flightService.fetchById(id);
        return ResponseEntity.status(HttpStatus.OK).body(flightResponseDto);
    }

    @Operation(
            summary = "Fetch all Flights Details REST API",
            description = "REST API to All fetch Flights"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            )
    }
    )
    @GetMapping(FETCH_ALL)
    public ResponseEntity<List<Flights>> fetchAllFlights() {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.findAll());
    }

    @Operation(
            summary = "Delete Flight REST API",
            description = "REST API to delete Flight based on String id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "1233",
                    description = "Flight could not find with giving string id",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @DeleteMapping(DELETE)
    public ResponseEntity<ResponseDto> deleteFlight(@RequestBody @Valid DeleteFlightRequestDto dto) {
        flightService.deleteFlight(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(STATUS_200, MESSAGE_200));
    }

    @Operation(
            summary = "Update Flight Details REST API",
            description = "REST API to update Flight details based on string id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "1233",
                    description = "Flight could not find with giving string id",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @PutMapping(UPDATE)
    public ResponseEntity<ResponseDto> updateFlight(@RequestBody @Valid UpdateFlightRequestDto dto) {
        boolean isUpdated = flightService.updateFlight(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(STATUS_200, MESSAGE_200));
    }

    @Operation(
            summary = "Search flights in all of saved flights REST API",
            description = "REST API to Search Flight details based on given details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "4211",
                    description = "Parameter error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    }
    )
    @PostMapping(SEARCH_FLIGHT)
    public ResponseEntity<List<FlightResponseDto>> searchFlight(@RequestBody @Valid FlightSearchRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.searchFlight(dto));
    }
}
