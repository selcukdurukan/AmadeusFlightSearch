package com.amadeus.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
@EqualsAndHashCode(callSuper = true)
public class Flights extends BaseEntity{

    @Id
    private String id;
    private String departureAirport;
    private String arrivalAirport;
    @JsonFormat(pattern="dd/MM/yyyy - HH:mm")
    private Date departureTime;
    @JsonFormat(pattern="dd/MM/yyyy - HH:mm")
    private Date arrivalTime;
    private double price;

}
