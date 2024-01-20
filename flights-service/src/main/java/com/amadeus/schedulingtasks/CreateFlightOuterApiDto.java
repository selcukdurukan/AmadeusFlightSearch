package com.amadeus.schedulingtasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFlightOuterApiDto {
    private String id;
    private String departureAirport;
    private String arrivalAirport;
    private Date departureTime;
    private Date arrivalTime;
    private double price;
}
