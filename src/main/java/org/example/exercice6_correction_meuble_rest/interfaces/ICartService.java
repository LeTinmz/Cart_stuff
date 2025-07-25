package org.example.exercice6_correction_meuble_rest.interfaces;

import org.example.exercice6_correction_meuble_rest.model.dto.CartItemAddDTO;
import org.example.exercice6_correction_meuble_rest.model.dto.CartItemDisplayDTO;
import org.example.exercice6_correction_meuble_rest.model.entity.Cart;
import org.example.exercice6_correction_meuble_rest.model.entity.CartItem;

import java.util.List;
import java.util.UUID;

public interface ICartService {
    Cart createCart();
    List<CartItemDisplayDTO> getCartItems(UUID id);
    CartItemAddDTO addCartItem(CartItemAddDTO cartItem);
    boolean removeCartItem(CartItemAddDTO cartItem);
    boolean clearCart();
    Cart getCartById(UUID id);
}
