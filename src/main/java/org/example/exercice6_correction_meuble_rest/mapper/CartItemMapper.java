package org.example.exercice6_correction_meuble_rest.mapper;

import org.example.exercice6_correction_meuble_rest.model.dto.CartItemAddDTO;
import org.example.exercice6_correction_meuble_rest.model.dto.CartItemDisplayDTO;
import org.example.exercice6_correction_meuble_rest.model.dto.FurnitureDTO;
import org.example.exercice6_correction_meuble_rest.model.entity.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CartItemMapper {

    public static CartItemDisplayDTO toDTO(CartItem cartItem) {
        return new CartItemDisplayDTO(cartItem.getId(),
                cartItem.getFurniture().getName(),
                cartItem.getFurniture().getDescription(),
                cartItem.getFurniture().getPrice(),
                cartItem.getQuantity());
    }

    public static List<CartItemDisplayDTO> toDTOs(List<CartItem> cartItems) {
        return cartItems.stream().map(CartItemMapper::toDTO).toList();
    }

    public static CartItemAddDTO toAddDTO(CartItem cartItem) {
        return new CartItemAddDTO(cartItem.getId(), cartItem.getQuantity());
    }
}
