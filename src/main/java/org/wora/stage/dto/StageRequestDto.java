package org.wora.stage.dto;

import java.time.LocalDate;

public record StageRequestDto(
        String name,
        double distance,
        LocalDate date,
        String competitionName
) {
}
