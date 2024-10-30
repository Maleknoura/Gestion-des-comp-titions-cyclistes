package org.wora.generalResult;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.wora.generalResult.dto.GeneralResultRequestDto;
import org.wora.generalResult.dto.GeneralResultResponseDto;

import java.util.List;

@Mapper
public interface GeneralResultMapper {
    GeneralResultMapper INSTANCE = Mappers.getMapper(GeneralResultMapper.class);

    @Mapping(target = "cyclistId", source = "cyclist.id")
    @Mapping(target = "competitionId", source = "competition.id")
//    @Mapping(target = "competitionName", source = "competition.name")
    GeneralResultResponseDto toDto(GeneralResult generalResult);

    @Mapping(target = "cyclist.id", source = "cyclistId")
    @Mapping(target = "competition.id", source = "competitionId")
    GeneralResult toEntity(GeneralResultRequestDto generalResultRequestDto);

    void updateEntityFromDto(GeneralResultRequestDto generalResultRequestDto, @MappingTarget GeneralResult generalResult);

    List<GeneralResultResponseDto> toDtoList(List<GeneralResult> generalResults);

    List<GeneralResult> toEntityList(List<GeneralResultRequestDto> generalResultRequestDtos);
}
