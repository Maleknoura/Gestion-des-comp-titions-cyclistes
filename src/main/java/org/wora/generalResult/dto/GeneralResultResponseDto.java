package org.wora.generalResult.dto;

import java.time.Duration;

public record GeneralResultResponseDto(Long cyclistId,
                                       String cyclistName,
                                       Long competitionId,
                                       Duration generalTime,
                                       Integer generalRank ) {
}
