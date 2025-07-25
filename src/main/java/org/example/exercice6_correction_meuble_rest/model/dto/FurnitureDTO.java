package org.example.exercice6_correction_meuble_rest.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.example.exercice6_correction_meuble_rest.model.entity.Furniture;

import java.util.UUID;

public record FurnitureDTO (
        UUID id,
        @NotBlank @Size(min = 3, max = 70) String name,
        @NotBlank String description,
        @Positive double price,
        @PositiveOrZero int stock) {}