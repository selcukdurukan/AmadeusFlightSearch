package com.amadeus.mapper;

import com.amadeus.dto.request.CreateAirportRequestDto;
import com.amadeus.dto.response.AirportResponseDto;
import com.amadeus.dto.response.ResponseDto;
import com.amadeus.repository.entity.Airports;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAirportMapper {

    public static final IAirportMapper INSTANCE = Mappers.getMapper(IAirportMapper.class);

    Airports toAirport(final CreateAirportRequestDto dto);
    AirportResponseDto toAirportResponseDto(final Airports airports);
}
