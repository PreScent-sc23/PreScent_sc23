package net.prescent.repository;

import net.prescent.entity.CartEntity;
import net.prescent.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {

    Optional<CartItemEntity> findByCartKeyAndFpKey(Integer cartKey, Integer fpKey);
}
