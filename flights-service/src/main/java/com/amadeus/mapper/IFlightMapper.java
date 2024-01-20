package com.amadeus.mapper;

import com.amadeus.dto.request.CreateFlightRequestDto;
import com.amadeus.dto.response.FlightResponseDto;
import com.amadeus.repository.entity.Flights;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IFlightMapper {

    public static final IFlightMapper INSTANCE = Mappers.getMapper(IFlightMapper.class);

    Flights toFlight(final CreateFlightRequestDto dto);

    FlightResponseDto toFlightResponseDto(final Flights flight);

    List<FlightResponseDto> toFlightResponseDtos(final List<Flights> flightsList);
}
