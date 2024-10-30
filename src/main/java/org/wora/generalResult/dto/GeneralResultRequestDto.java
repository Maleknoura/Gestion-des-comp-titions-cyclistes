package org.wora.generalResult.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;

import java.time.Duration;

public record GeneralResultRequestDto(Long cyclistId,
                                      Long competitionId,
                                      Duration generalTime,
                                      Integer generalRank) {
}
