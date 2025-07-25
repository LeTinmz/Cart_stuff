package org.example.exercice6_correction_meuble_rest.Exceptions;

import org.example.exercice6_correction_meuble_rest.model.dto.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ExceptionDTO> handleOutOfStockException(OutOfStockException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), ex.getHttpStatus().value(), ex.getTime());
        return new ResponseEntity<>(exceptionDTO, ex.getHttpStatus());
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleCartItemNotFoundException(CartItemNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), ex.getHttpStatus().value(), ex.getTime());
        return new ResponseEntity<>(exceptionDTO, ex.getHttpStatus());
    }

    @ExceptionHandler(FurnitureNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleFurnitureNotFoundException(FurnitureNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), ex.getHttpStatus().value(), ex.getTime());
        return new ResponseEntity<>(exceptionDTO, ex.getHttpStatus());
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleCartNotFoundException(CartNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), ex.getHttpStatus().value(), ex.getTime());
        return new ResponseEntity<>(exceptionDTO, ex.getHttpStatus());
    }
}
