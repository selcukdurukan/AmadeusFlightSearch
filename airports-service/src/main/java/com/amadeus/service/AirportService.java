package com.amadeus.service;

import com.amadeus.dto.request.CreateAirportRequestDto;
import com.amadeus.dto.request.DeleteAirportRequestDto;
import com.amadeus.dto.request.UpdateAirportRequestDto;
import com.amadeus.dto.response.AirportResponseDto;
import com.amadeus.exception.AirportAppException;
import com.amadeus.exception.ErrorType;
import com.amadeus.mapper.IAirportMapper;
import com.amadeus.repository.IAirportRepository;
import com.amadeus.repository.entity.Airports;
import com.amadeus.utility.JwtTokenManager;
import com.amadeus.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportService extends ServiceManager<Airports, Long> {
    private final IAirportRepository repository;
    private final JwtTokenManager jwtTokenManager;

    public AirportService(IAirportRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public void createAirport(CreateAirportRequestDto dto) {
        if (!jwtTokenManager.validateToken(dto.getToken())) {
            throw new AirportAppException(ErrorType.INVALID_TOKEN);
        }
        Optional<Airports> checkAirportPresent = repository.findOptionalByAirportNameEqualsIgnoreCase(dto.getAirportName());
        if (checkAirportPresent.isPresent()) {
            throw new AirportAppException(ErrorType.CREATE_AIRPORT_ERROR);
        }
        Airports airport = IAirportMapper.INSTANCE.toAirport(dto);
        repository.save(airport);
    }

    public void createAirport2(CreateAirportRequestDto dto) {
        Optional<Airports> checkAirportPresent = repository.findOptionalByAirportNameEqualsIgnoreCase(dto.getAirportName());
        if (checkAirportPresent.isPresent()) {
            throw new AirportAppException(ErrorType.CREATE_AIRPORT_ERROR);
        }
        Airports airport = IAirportMapper.INSTANCE.toAirport(dto);
        repository.save(airport);
    }

    public AirportResponseDto fetchAirport(long id) {
        Optional<Airports> checkAirportPresent = repository.findById(id);
        if (checkAirportPresent.isEmpty()) {
            throw new AirportAppException(ErrorType.AIRPORT_NOT_FOUND_ERROR);
        }
        return IAirportMapper.INSTANCE.toAirportResponseDto(checkAirportPresent.get());
    }

    public void deleteAirport(DeleteAirportRequestDto dto) {
        if (!jwtTokenManager.validateToken(dto.getToken())) {
            throw new AirportAppException(ErrorType.INVALID_TOKEN);
        }
        Optional<Airports> checkAirportPresent = repository.findById(dto.getId());
        if (checkAirportPresent.isEmpty()) {
            throw new AirportAppException(ErrorType.AIRPORT_NOT_FOUND_ERROR);
        }
        repository.deleteById(dto.getId());
    }

    public boolean updateAirport(UpdateAirportRequestDto dto) {
        if (!jwtTokenManager.validateToken(dto.getToken())) {
            throw new AirportAppException(ErrorType.INVALID_TOKEN);
        }
        Optional<Airports> checkAirportPresent = repository.findById(dto.getAirportId());
        if (checkAirportPresent.isEmpty()) {
            throw new AirportAppException(ErrorType.AIRPORT_NOT_FOUND_ERROR);
        }
        Airports airports = checkAirportPresent.get();
        airports.setCity(dto.getCity() != null ? dto.getCity() : airports.getCity());
        airports.setAirportName(dto.getAirportName() != null ? dto.getAirportName() : airports.getAirportName());
        update(airports);
        return true;
    }

    public boolean fetchByAirportName(String name) {
        Optional<Airports> checkAirportPresent = repository.findOptionalByAirportNameEqualsIgnoreCase(name);
        return checkAirportPresent.isPresent();
    }
}
