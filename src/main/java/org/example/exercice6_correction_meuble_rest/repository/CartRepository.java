package org.example.exercice6_correction_meuble_rest.repository;

import org.example.exercice6_correction_meuble_rest.model.entity.Cart;
import org.example.exercice6_correction_meuble_rest.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
}
