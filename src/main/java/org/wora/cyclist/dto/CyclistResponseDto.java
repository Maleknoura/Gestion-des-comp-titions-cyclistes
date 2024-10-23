package org.wora.cyclist.dto;

import java.time.LocalDate;

public record CyclistResponseDto(Long id,
                                 String firstName,
                                 String lastName,
                                 LocalDate dateOfBirth,
                                 String nationality) {
}
