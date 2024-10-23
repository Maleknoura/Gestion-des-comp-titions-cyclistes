package org.wora.cyclist;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.wora.cyclist.dto.CyclistRequestDto;
import org.wora.cyclist.dto.CyclistResponseDto;

@Mapper
public interface CyclistMapper {

    CyclistMapper INSTANCE = Mappers.getMapper(CyclistMapper.class);

    Cyclist toEntity(CyclistRequestDto cyclistRequestDto);

    CyclistResponseDto toDto(Cyclist cyclist);

    void updateEntityFromDto(CyclistRequestDto cyclistRequestDto, @MappingTarget Cyclist cyclist);
}
