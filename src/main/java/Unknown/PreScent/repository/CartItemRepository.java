package Unknown.PreScent.repository;

import Unknown.PreScent.entity.CartEntity;
import Unknown.PreScent.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {

    Optional<CartItemEntity> findByCartKeyAndFpKey(Integer cartKey, Integer fpKey);
}
