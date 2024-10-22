package org.wora.competition.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public final class CompetitionResponseDto {
    private final Long id;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String location;

    public CompetitionResponseDto(Long id, String name, LocalDate startDate, LocalDate endDate, String location) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }
}

