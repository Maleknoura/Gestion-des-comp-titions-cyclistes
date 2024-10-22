package org.wora.competition.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public final class CreateCompetitionDto {
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String location;

    public CreateCompetitionDto(String name, LocalDate startDate, LocalDate endDate, String location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

}

