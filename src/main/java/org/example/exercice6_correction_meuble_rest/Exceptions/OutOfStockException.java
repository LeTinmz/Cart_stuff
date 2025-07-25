package org.example.exercice6_correction_meuble_rest.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter

public class OutOfStockException extends RuntimeException {
    private HttpStatus httpStatus;
    private LocalDateTime time;
    public OutOfStockException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.time = LocalDateTime.now();
    }
}
