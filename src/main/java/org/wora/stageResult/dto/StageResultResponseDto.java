package org.wora.stageResult.dto;

import java.time.Duration;

public record StageResultResponseDto(Long cyclistId, Long stageId, Duration time, Integer rank) {
}
