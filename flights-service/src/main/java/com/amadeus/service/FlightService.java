package com.amadeus.service;

import com.amadeus.dto.request.CreateFlightRequestDto;
import com.amadeus.dto.request.DeleteFlightRequestDto;
import com.amadeus.dto.request.FlightSearchRequestDto;
import com.amadeus.dto.request.UpdateFlightRequestDto;
import com.amadeus.dto.response.FlightResponseDto;
import com.amadeus.exception.ErrorType;
import com.amadeus.exception.FlightAppException;
import com.amadeus.manager.IAirportManager;
import com.amadeus.mapper.IFlightMapper;
import com.amadeus.repository.IFlightRepository;
import com.amadeus.repository.entity.Flights;
import com.amadeus.schedulingtasks.CreateFlightOuterApiDto;
import com.amadeus.utility.JwtTokenManager;
import com.amadeus.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService extends ServiceManager<Flights, String> {

    private final IFlightRepository repository;
    private final IAirportManager airportManager;
    private final JwtTokenManager jwtTokenManager;

    public FlightService(IFlightRepository repository, IAirportManager airportManager, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.airportManager = airportManager;
        this.jwtTokenManager = jwtTokenManager;
    }


    public void createFlight(CreateFlightRequestDto dto) {
        if (!jwtTokenManager.validateToken(dto.getToken())) {
            throw new FlightAppException(ErrorType.INVALID_TOKEN);
        }
        boolean foundDepartureAirport = airportManager.hasFoundByAirportName(dto.getDepartureAirport());
        boolean foundArrivalAirport = airportManager.hasFoundByAirportName(dto.getArrivalAirport());
        if (!foundArrivalAirport || !foundDepartureAirport) {
            throw new FlightAppException(ErrorType.CREATE_FLIGHT_ERROR);
        }
        Flights flight = IFlightMapper.INSTANCE.toFlight(dto);
        save(flight);
    }

    public boolean createFlightFromOuterApi(CreateFlightOuterApiDto dto) {
        Optional<Flights> flight  = findAllById(dto.getId());
        if (flight.isPresent()) {
            return false;
        } else {
            Flights createFlight = Flights.builder()
                    .id(dto.getId())
                    .departureAirport(dto.getDepartureAirport())
                    .arrivalAirport(dto.getArrivalAirport())
                    .departureTime(dto.getDepartureTime())
                    .arrivalTime(dto.getArrivalTime())
                    .price(dto.getPrice())
                    .build();
            save(createFlight);
            return true;
        }
    }

    public FlightResponseDto fetchById(String id) {
        Optional<Flights> flight = findAllById(id);
        if (flight.isEmpty()) {
            throw new FlightAppException(ErrorType.FLIGHT_NOT_FOUND_ERROR);
        }
        return IFlightMapper.INSTANCE.toFlightResponseDto(flight.get());
    }

    public void deleteFlight(DeleteFlightRequestDto dto) {
        if (!jwtTokenManager.validateToken(dto.getToken())) {
            throw new FlightAppException(ErrorType.INVALID_TOKEN);
        }
        Optional<Flights> checkFlightPresent = repository.findById(dto.getId());
        if (checkFlightPresent.isEmpty()) {
            throw new FlightAppException(ErrorType.FLIGHT_NOT_FOUND_ERROR);
        }
        repository.deleteById(dto.getId());
    }

    public boolean updateFlight(UpdateFlightRequestDto dto) {
        if (!jwtTokenManager.validateToken(dto.getToken())) {
            throw new FlightAppException(ErrorType.INVALID_TOKEN);
        }
        if (dto.getArrivalAirport().equalsIgnoreCase(dto.getDepartureAirport())) {
            throw new FlightAppException(ErrorType.UPDATE_SAME_AIRPORTS_ERROR);
        }
        Optional<Flights> checkFlightPresent = repository.findById(dto.getId());
        if (checkFlightPresent.isEmpty()) {
            throw new FlightAppException(ErrorType.FLIGHT_NOT_FOUND_ERROR);
        }
        if (dto.getDepartureAirport() != null) {
            boolean foundDepartureAirport = airportManager.hasFoundByAirportName(dto.getDepartureAirport());
            if (!foundDepartureAirport) {
                throw new FlightAppException(ErrorType.CREATE_FLIGHT_ERROR);
            }
        }
        if (dto.getArrivalAirport() != null) {
            boolean foundArrivalAirport = airportManager.hasFoundByAirportName(dto.getArrivalAirport());
            if (!foundArrivalAirport) {
                throw new FlightAppException(ErrorType.CREATE_FLIGHT_ERROR);
            }
        }
        Flights flights = getFlights(dto, checkFlightPresent);
        update(flights);
        return true;
    }

    private static Flights getFlights(UpdateFlightRequestDto dto, Optional<Flights> checkFlightPresent) {
        Flights flights = checkFlightPresent.get();
        flights.setDepartureAirport(dto.getDepartureAirport() != null ? dto.getDepartureAirport() : flights.getDepartureAirport());
        flights.setArrivalAirport(dto.getArrivalAirport() != null ? dto.getArrivalAirport() : flights.getArrivalAirport());
        flights.setDepartureTime(dto.getDepartureTime() != null ? dto.getDepartureTime() : flights.getDepartureTime());
        flights.setArrivalTime(dto.getArrivalTime() != null ? dto.getArrivalTime() : flights.getArrivalTime());
        flights.setPrice(dto.getPrice() != null ? dto.getPrice() : flights.getPrice());
        return flights;
    }

    public List<FlightResponseDto> searchFlight(FlightSearchRequestDto dto) {
        LocalDate date = dto.getDepartureDate();
        Instant startOfTheDay = date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
        Instant endOfTheDay = date.plusDays(1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
        List<Flights> flightsList = repository.searchByQuery(startOfTheDay, endOfTheDay, dto.getDepartureAirport(), dto.getArrivalAirport());
        if (dto.getReturnDate() != null) {
            date = dto.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            startOfTheDay = date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
            endOfTheDay = date.plusDays(1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
            List<Flights> returnFlightList = repository.searchByQuery(startOfTheDay, endOfTheDay, dto.getArrivalAirport(), dto.getDepartureAirport());
            flightsList.addAll(returnFlightList);
        }
        return IFlightMapper.INSTANCE.toFlightResponseDtos(flightsList);
    }

}
