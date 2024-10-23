package org.wora.competition;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.wora.competition.dto.CompetitionRequestDto;
import org.wora.competition.dto.CompetitionResponseDto;

@Mapper(componentModel = "spring")

public interface CompetitionMapper {
    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    Competition toEntity(CompetitionRequestDto dto);
    CompetitionResponseDto toDto(Competition entity);

    void updateEntityFromDto(CompetitionRequestDto dto, @MappingTarget Competition existingCompetition);
}

