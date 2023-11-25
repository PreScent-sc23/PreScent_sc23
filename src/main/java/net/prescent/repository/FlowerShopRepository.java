package net.prescent.repository;

import net.prescent.entity.FlowerShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlowerShopRepository extends JpaRepository<FlowerShopEntity, Integer> {
    Optional<FlowerShopEntity> findByshopKey(Integer shopKey);

}
