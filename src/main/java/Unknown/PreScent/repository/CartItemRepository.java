package Unknown.PreScent.repository;

import Unknown.PreScent.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {
}
