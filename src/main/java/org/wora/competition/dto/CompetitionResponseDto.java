package org.wora.competition.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.wora.stage.dto.StageResponseDto;

import java.time.LocalDate;
import java.util.Set;

public record CompetitionResponseDto(
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String location,
        Set<StageResponseDto> stages
) {
}
