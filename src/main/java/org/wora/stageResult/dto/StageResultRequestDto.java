package org.wora.stageResult.dto;

import java.time.Duration;

public record StageResultRequestDto(Long cyclistId, Long stageId, Duration time, Integer rank) {
}
