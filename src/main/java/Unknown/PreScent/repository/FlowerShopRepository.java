package Unknown.PreScent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Unknown.PreScent.entity.FlowerShopEntity;

import java.util.Optional;

public interface FlowerShopRepository extends JpaRepository<FlowerShopEntity, Integer> {
    Optional<FlowerShopEntity> findByshopKey(Integer shopKey);

}
