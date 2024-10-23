package org.wora.team;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.wora.team.dto.TeamRequestDto;
import org.wora.team.dto.TeamResponseDto;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    Team toEntity(TeamRequestDto dto);

    TeamResponseDto toDto(Team entity);

    void updateEntityFromDto(TeamRequestDto dto, @MappingTarget Team existingTeam);
}
