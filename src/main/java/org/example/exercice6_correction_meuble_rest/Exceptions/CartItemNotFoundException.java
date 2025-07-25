package org.example.exercice6_correction_meuble_rest.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
public class CartItemNotFoundException extends RuntimeException {
  private HttpStatus httpStatus;
  private LocalDateTime time;

    public CartItemNotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.time = LocalDateTime.now();
    }
}
