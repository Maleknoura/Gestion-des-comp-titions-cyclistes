package org.wora.cyclist.dto;

import java.time.LocalDate;

public record CyclistResponseDto(
                                 String firstName,
                                 String lastName,
                                 LocalDate dateOfBirth,
                                 String nationality) {

}
