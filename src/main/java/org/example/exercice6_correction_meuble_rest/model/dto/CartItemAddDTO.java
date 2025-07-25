package org.example.exercice6_correction_meuble_rest.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CartItemAddDTO (
        @NotNull UUID furnitureID,
        @Positive int quantity
){}