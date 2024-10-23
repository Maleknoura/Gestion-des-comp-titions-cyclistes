package org.wora.competition.dto;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
public record CompetitionRequestDto
(
        String name,

        LocalDate startDate,

        LocalDate endDate,

        String location
) {}




