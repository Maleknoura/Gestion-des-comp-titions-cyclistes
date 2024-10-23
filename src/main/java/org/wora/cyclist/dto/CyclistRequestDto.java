package org.wora.cyclist.dto;

import java.time.LocalDate;

public record CyclistRequestDto(String firstName,
                                String lastName,
                                LocalDate dateOfBirth,
                                String nationality) {
}
