package org.example.exercice6_correction_meuble_rest.model.dto;

import java.time.LocalDateTime;

public record ExceptionDTO(String message, int status, LocalDateTime time) {}

