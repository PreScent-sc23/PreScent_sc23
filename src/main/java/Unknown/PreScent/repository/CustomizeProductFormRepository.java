package Unknown.PreScent.repository;

import Unknown.PreScent.entity.CustomizeProductFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizeProductFormRepository extends JpaRepository<CustomizeProductFormEntity, Integer> {
    public CustomizeProductFormEntity findByCpKey(Integer cpKey);
}
