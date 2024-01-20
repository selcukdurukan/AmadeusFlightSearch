package com.amadeus.manager;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.amadeus.constants.FlightsConstant.*;

@FeignClient(name = "airports-service", url = "${application-feign.airport.feign-url}", dismiss404 = true)
public interface IAirportManager {

    @GetMapping(HAS_FOUND_BY_NAME)
    @Operation(summary = "Check airport whether found or not")
    public boolean hasFoundByAirportName(@PathVariable String name);


}
