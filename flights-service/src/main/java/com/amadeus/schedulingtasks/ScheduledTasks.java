package com.amadeus.schedulingtasks;


import com.amadeus.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final FlightService flightService;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    @Value(value = "${scheduled-outer.url}")
    private String apiUrl;



    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Scheduled(fixedRate = 3 * 60 * 1000, initialDelay = 10000)
    public void reportCurrentTime() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        FlightApiTemplate[] fromApis = restTemplate.getForObject(apiUrl, FlightApiTemplate[].class);
        List<FlightApiTemplate> listApis = new ArrayList<>(Arrays.asList(fromApis));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        for (FlightApiTemplate f : listApis) {
            CreateFlightOuterApiDto createFlightOuterApiDto = CreateFlightOuterApiDto.builder()
                    .id(f.getId())
                    .departureAirport(f.getDepartureAirport())
                    .arrivalAirport(f.getArrivalAirport())
                    .departureTime(formatter.parse(f.getDepartureTime()))
                    .arrivalTime(formatter.parse(f.getArrivalTime()))
                    .price(f.getPrice())
                    .build();
          boolean hasSavedOuterFlight =  flightService.createFlightFromOuterApi(createFlightOuterApiDto);
            if (hasSavedOuterFlight) {
                log.info("This saved from the outer api - {}", createFlightOuterApiDto);
            } else {
                log.warn("Not saved due to already been in db - {}", createFlightOuterApiDto);
            }
        }
    }
}
