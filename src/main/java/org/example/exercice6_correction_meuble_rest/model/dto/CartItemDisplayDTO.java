package org.example.exercice6_correction_meuble_rest.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CartItemDisplayDTO(
        UUID id,
        String furnitureName,
        String furnitureDescription,
        double furniturePrice,
        int quantity
) {}
