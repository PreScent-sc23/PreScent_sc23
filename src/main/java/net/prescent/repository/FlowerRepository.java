package net.prescent.repository;

import net.prescent.entity.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<FlowerEntity, Long> {
    FlowerEntity findByName(String name);
}
