package net.prescent.repository;

import net.prescent.entity.FlowerShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FlowerShopRepository extends JpaRepository<FlowerShopEntity, Integer> {
    Optional<FlowerShopEntity> findByshopKey(Integer shopKey);
    @Query("SELECT DISTINCT fs FROM FlowerShopEntity fs LEFT JOIN FETCH fs.finishedProductEntityList")
    Optional<FlowerShopEntity> findByIdWithProducts(Integer shopKey);
}
