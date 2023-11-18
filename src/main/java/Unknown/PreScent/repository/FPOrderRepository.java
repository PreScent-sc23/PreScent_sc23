package Unknown.PreScent.repository;

import Unknown.PreScent.entity.FPOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FPOrderRepository extends JpaRepository<FPOrderEntity, Integer> {
}
