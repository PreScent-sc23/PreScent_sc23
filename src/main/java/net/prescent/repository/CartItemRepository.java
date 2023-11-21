package net.prescent.repository;

import net.prescent.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {
}
