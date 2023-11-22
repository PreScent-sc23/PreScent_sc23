package net.prescent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.prescent.entity.FlowerShopEntity;

import java.util.Optional;

public interface FlowerShopRepository extends JpaRepository<FlowerShopEntity, Integer> {
    Optional<FlowerShopEntity> findByshopKey(Integer shopKey);

}
