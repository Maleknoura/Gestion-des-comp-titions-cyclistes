package org.wora.competition.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public final class CompetitionRequestDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;

    public CompetitionRequestDto(String name, LocalDate startDate, LocalDate endDate, String location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }
}


