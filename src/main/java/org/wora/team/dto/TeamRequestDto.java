package org.wora.team.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record TeamRequestDto(
                @NotBlank(message = "Le nom ne peut pas être null")
                @Size(min = 2, max = 50, message = "Le nom doit avoir entre 2 et 50 caractères")
                String name
        ) {
        }
