package org.wora.competition.dto;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record CompetitionRequestDto
(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Start date is required")
        @Future(message = "Start date must be in the future")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        @Future(message = "End date must be in the future")
        LocalDate endDate,

        @NotBlank(message = "Location is required")
        String location

) {
    public CompetitionRequestDto {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("The end date must be after the start date.");
        }
    }
}




