package org.wora.stage.dto;

import org.wora.competition.Competition;
import org.wora.stageResult.dto.StageResultResponseDto;

import java.time.LocalDate;
import java.util.List;

public record StageResponseDto(
        String name,
        double distance,
        LocalDate date,
        String competitionName
) {
}
