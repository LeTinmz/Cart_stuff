package org.example.exercice6_correction_meuble_rest.interfaces;

import org.example.exercice6_correction_meuble_rest.model.dto.CartItemAddDTO;
import org.example.exercice6_correction_meuble_rest.model.dto.CartItemDisplayDTO;

import java.util.List;
import java.util.UUID;

public interface ICartItemService {
    List<CartItemDisplayDTO> getCartItems(UUID id);
    CartItemDisplayDTO addCartItem(CartItemAddDTO cartItemAddDTO);
    boolean removeCartItem(UUID id);
    boolean clearCart();
}
