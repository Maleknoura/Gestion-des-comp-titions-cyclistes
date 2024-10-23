package org.wora.stage.dto;

import org.wora.competition.Competition;

import java.time.LocalDate;

public record StageResponseDto(
        String name,
        double distance,
        LocalDate date,
        String CompetitionName
) {
}
