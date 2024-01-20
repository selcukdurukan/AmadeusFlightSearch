package com.amadeus.utility;

import com.amadeus.dto.request.CreateAirportRequestDto;
import com.amadeus.repository.entity.Airports;
import com.amadeus.service.AirportService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl {

    private final AirportService service;

    @PostConstruct
    public void saveSomeAirport() {
        saveAirport();
    }

    private void saveAirport() {
        CreateAirportRequestDto adana = CreateAirportRequestDto.builder().city("Adana").airportName("Şakirpaşa Havalimanı").build();
        CreateAirportRequestDto alanya = CreateAirportRequestDto.builder().city("Alanya").airportName("Gazipaşa Havalimanı").build();
        CreateAirportRequestDto erzurum = CreateAirportRequestDto.builder().city("Erzurum").airportName("Erzurum Havalimanı").build();
        CreateAirportRequestDto hatay = CreateAirportRequestDto.builder().city("Hatay").airportName("Hatay Havalimanı").build();
        List<CreateAirportRequestDto> dtos = new LinkedList<>(Arrays.asList(adana, alanya, erzurum, hatay));
        for (CreateAirportRequestDto dto : dtos) {
            try {
                service.createAirport2(dto);
            } catch (Exception e) {
                System.out.println("hehehe");
            }
        }
    }
}
