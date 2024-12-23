package org.wora.stageResult;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.wora.stageResult.dto.StageResultRequestDto;
import org.wora.stageResult.dto.StageResultResponseDto;

import java.util.List;
@Mapper
public interface StageResultMapper {
    StageResultMapper INSTANCE = Mappers.getMapper(StageResultMapper.class);

    @Mapping(target = "stageId", source = "stage.id")
    @Mapping(target = "cyclistId", source = "cyclist.id")
    StageResultResponseDto toDto(StageResult stageResult);

    StageResult toEntity(StageResultRequestDto stageRequestDto);

    void updateEntityFromDto(StageResultRequestDto stageResultRequestDto, @MappingTarget StageResult stageResult);

    List<StageResultResponseDto> toDtoList(List<StageResult> stageResults);

    List<StageResult> toEntityList(List<StageResultRequestDto> stageResultRequestDtos);

}
