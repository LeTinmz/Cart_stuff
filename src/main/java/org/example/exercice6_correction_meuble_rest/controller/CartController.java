package org.example.exercice6_correction_meuble_rest.controller;

import org.example.exercice6_correction_meuble_rest.model.dto.CartDisplayDTO;
import org.example.exercice6_correction_meuble_rest.model.dto.CartItemAddDTO;
import org.example.exercice6_correction_meuble_rest.model.dto.CartItemDisplayDTO;
import org.example.exercice6_correction_meuble_rest.model.entity.Cart;
import org.example.exercice6_correction_meuble_rest.model.entity.CartItem;
import org.example.exercice6_correction_meuble_rest.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

        @GetMapping("/coucou")
        public String sayCoucou(){
        return "coucou";
        }

    @PostMapping
    public ResponseEntity<CartDisplayDTO> createCart() {
        return ResponseEntity.ok(cartService.createCart());
    }


    @GetMapping("/{cartId}")
    public ResponseEntity<CartDisplayDTO> getCartItems(@PathVariable UUID cartId) {
        return ResponseEntity.ok(cartService.getCartDisplayDTO(cartId));
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<List<CartItemDisplayDTO>> addCartItem(@PathVariable UUID cartId, @RequestBody CartItemAddDTO dto) {
        return ResponseEntity.ok(cartService.addCartItemToCart(cartId, dto));
    }

    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public ResponseEntity<List<CartItemDisplayDTO>> removeCartItem(@PathVariable UUID cartId, @PathVariable UUID cartItemId) {
        return ResponseEntity.ok(cartService.removeCartItemFromCart(cartId, cartItemId));
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<CartDisplayDTO> clearCart(@PathVariable UUID cartId) {
        return ResponseEntity.ok(cartService.clearCart(cartId));
    }
}
