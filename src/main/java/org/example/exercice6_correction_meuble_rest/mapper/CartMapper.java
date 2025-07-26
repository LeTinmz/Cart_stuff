package org.example.exercice6_correction_meuble_rest.mapper;

import org.example.exercice6_correction_meuble_rest.model.dto.CartDisplayDTO;
import org.example.exercice6_correction_meuble_rest.model.entity.Cart;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartDisplayDTO toDto(Cart cart) {
        return new CartDisplayDTO(
                cart.getId(),cart.getItems().stream()
                .map(CartItemMapper::toDTO)
                .toList(),cart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getFurniture().getPrice())
                .sum()
        );
    }
}
