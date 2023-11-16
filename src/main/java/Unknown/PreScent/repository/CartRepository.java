package Unknown.PreScent.repository;

import Unknown.PreScent.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    Optional<CartEntity> findByCartKey(Integer cartKey);

    Optional<CartEntity> findByCustomerKey(Integer customerKey);


}
