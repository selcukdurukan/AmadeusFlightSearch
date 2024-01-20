package com.amadeus.mapper;

import com.amadeus.dto.request.RegisterAuthRequestDto;
import com.amadeus.repository.entity.Auths;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auths toAuth(final RegisterAuthRequestDto dto);
}
