package Unknown.PreScent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Unknown.PreScent.dto.FlowerShop;

import java.util.Optional;

public interface FlowerShopRepository extends JpaRepository<FlowerShop, Integer> {
    Optional<FlowerShop> findByshopKey(Integer shopKey);
}
