package org.example.exercice6_correction_meuble_rest.service;

import org.example.exercice6_correction_meuble_rest.Exceptions.CartItemNotFoundException;
import org.example.exercice6_correction_meuble_rest.Exceptions.CartNotFoundException;
import org.example.exercice6_correction_meuble_rest.Exceptions.FurnitureNotFoundException;
import org.example.exercice6_correction_meuble_rest.Exceptions.OutOfStockException;
import org.example.exercice6_correction_meuble_rest.interfaces.ICartService;
import org.example.exercice6_correction_meuble_rest.mapper.CartItemMapper;
import org.example.exercice6_correction_meuble_rest.mapper.CartMapper;
import org.example.exercice6_correction_meuble_rest.model.dto.CartDisplayDTO;
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

    public CartDisplayDTO createCart() {
        Cart cart = Cart.builder().build();
        Cart savedCart = cartRepository.save(cart);
        return CartMapper.toDto(savedCart);
    }

    public Cart getCart(UUID cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));
    }

    public CartDisplayDTO getCartDisplayDTO(UUID cartId) {
        Cart cart = getCart(cartId);
        return CartMapper.toDto(cart);
    }

    public List<CartItemDisplayDTO> getAllItemsFromCart(UUID id) {
        Cart cart = getCart(id);
        return cart.getItems().stream().map(CartItemMapper::toDTO).toList();

    }
    public List<CartItemDisplayDTO> addCartItemToCart(UUID cartId, CartItemAddDTO dto) {
        Cart cart = getCart(cartId);
        Furniture furniture = furnitureRepository.findById(dto.furnitureId())
                .orElseThrow(() -> new FurnitureNotFoundException("Furniture not found"));

        int quantity = dto.quantity();
        if(quantity>furniture.getStock()){
            throw new OutOfStockException("yaplu");
        }
        furniture.setStock(furniture.getStock()-quantity);
        cart.getItems().add(CartItem.builder().cart(cart).furniture(furniture).quantity(quantity).build());
        cartRepository.save(cart);
        return CartItemMapper.toDTOs(cart.getItems());
    }

    public List<CartItemDisplayDTO> removeCartItemFromCart(UUID cartId, UUID cartItemId) {
        Cart cart = getCart(cartId);
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found in this cart"));
        cart.getItems().remove(cartItem);
        cartItem.getFurniture().setStock(cartItem.getFurniture().getStock()+cartItem.getQuantity());
        cartRepository.save(cart);
        return CartItemMapper.toDTOs(cart.getItems());
    }


    public CartDisplayDTO clearCart(UUID cartId) {
        Cart cart = getCart(cartId);
        cart.getItems().forEach(item ->
                item.getFurniture().setStock(item.getFurniture().getStock() + item.getQuantity())
        );
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        Cart savedCart = cartRepository.save(cart);
        return CartMapper.toDto(savedCart);
    }
}
