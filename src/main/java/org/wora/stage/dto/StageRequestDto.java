package org.wora.stage.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record StageRequestDto(
        @NotBlank(message = "The name is required")
        String name,

        @NotNull(message = "Distance cannot be null")
        @Positive(message = "Distance must be positive")
        double distance,

        @NotNull(message = "Date cannot be null")
        @Past(message = "The date must not be in the past")
        LocalDate date,

        @NotBlank(message = "Competition name is required")
        String competitionName
) {
}
