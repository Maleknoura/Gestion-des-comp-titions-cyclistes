package org.wora.competition.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CompetitionResponseDto(
                                     String name,

                                     LocalDate startDate,

                                     LocalDate endDate,

                                     String location) {
}
