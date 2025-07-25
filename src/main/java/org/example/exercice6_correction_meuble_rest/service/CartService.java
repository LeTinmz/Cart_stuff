package org.example.exercice6_correction_meuble_rest.service;

import org.example.exercice6_correction_meuble_rest.Exceptions.CartItemNotFoundException;
import org.example.exercice6_correction_meuble_rest.Exceptions.CartNotFoundException;
import org.example.exercice6_correction_meuble_rest.Exceptions.FurnitureNotFoundException;
import org.example.exercice6_correction_meuble_rest.Exceptions.OutOfStockException;
import org.example.exercice6_correction_meuble_rest.interfaces.ICartService;
import org.example.exercice6_correction_meuble_rest.mapper.CartItemMapper;
import org.example.exercice6_correction_meuble_rest.model.dto.CartItemAddDTO;
import org.example.exercice6_correction_meuble_rest.model.dto.CartItemDisplayDTO;
import org.example.exercice6_correction_meuble_rest.model.entity.Cart;
import org.example.exercice6_correction_meuble_rest.model.entity.CartItem;
import org.example.exercice6_correction_meuble_rest.model.entity.Furniture;
import org.example.exercice6_correction_meuble_rest.repository.CartItemRepository;
import org.example.exercice6_correction_meuble_rest.repository.CartRepository;
import org.example.exercice6_correction_meuble_rest.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FurnitureRepository furnitureRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,FurnitureRepository furnitureRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.furnitureRepository=furnitureRepository;
    }

    public Cart createCart() {
        Cart cart = Cart.builder().build();
        return cartRepository.save(cart);
    }

    public Cart getCart(UUID cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));
    }

    public List<CartItemDisplayDTO> getAllItemsFromCart(UUID id) {
        Cart cart = getCart(id);
        return cart.getItems().stream().map(CartItemMapper::toDTO).toList();

    }
    public List<CartItemDisplayDTO> addCartItemToCart(UUID cartId, CartItemAddDTO dto) {
        Cart cart = getCart(cartId);
        Furniture furniture = furnitureRepository.findById(dto.furnitureID())
                .orElseThrow(() -> new FurnitureNotFoundException("Furniture not found"));

        int quantity = dto.quantity();
        if(quantity>furniture.getStock()){
            throw new OutOfStockException("yaplu");
        }
        cart.getItems().add(new CartItem(furniture,quantity));
        cartRepository.save(cart);
        return CartItemMapper.toDTOs(cart.getItems());
    }

    public List<CartItemDisplayDTO> removeCartItemFromCart(UUID cartId, UUID cartItemId) {
        Cart cart = getCart(cartId);
        List<CartItem> updatedItems = cart.getItems().stream().filter(item -> !item.getId().equals(cartItemId)).toList();
        if(updatedItems.isEmpty()){
            throw new CartItemNotFoundException("yapa");
        }
        cart.setItems(updatedItems);
        cartItemRepository.deleteById(cartItemId);
        cartRepository.save(cart);
        return CartItemMapper.toDTOs(cart.getItems());
    }


    public Cart clearCart(UUID cartId) {
        Cart cart = getCart(cartId);
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
