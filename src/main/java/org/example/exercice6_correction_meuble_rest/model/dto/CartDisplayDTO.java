package org.example.exercice6_correction_meuble_rest.model.dto;

import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public record CartDisplayDTO(
        UUID id,
        List<CartItemDisplayDTO> items,
        @Positive double total
) {
}
