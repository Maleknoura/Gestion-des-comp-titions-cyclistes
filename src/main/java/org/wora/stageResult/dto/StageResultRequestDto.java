package org.wora.stageResult.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record StageResultRequestDto(
        @NotNull(message="the cyclist id is required")
        Long cyclistId,
        @NotNull(message="the stage id is required")
        Long stageId,
        Duration time,
        Integer rank) {
}
