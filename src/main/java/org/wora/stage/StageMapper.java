package org.wora.stage;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.wora.stage.dto.StageRequestDto;
import org.wora.stage.dto.StageResponseDto;
import org.wora.stage.Stage;
import org.wora.competition.Competition;

import java.util.List;

@Mapper
public interface StageMapper {
    StageMapper INSTANCE = Mappers.getMapper(StageMapper.class);
    @Mapping(target = "competitionName", source = "competition.name")
    StageResponseDto toDto(Stage stage);

    Stage toEntity(StageRequestDto stageRequestDto);

    void updateEntityFromDto(StageRequestDto stageRequestDto, @MappingTarget Stage stage);

    List<StageResponseDto> toDtoList(List<Stage> stages);

    List<Stage> toEntityList(List<StageRequestDto> stageRequestDtos);
}

