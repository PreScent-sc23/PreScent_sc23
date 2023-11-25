package net.prescent.repository;

import net.prescent.entity.FPOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FPOrderRepository extends JpaRepository<FPOrderEntity, Integer> {
}
