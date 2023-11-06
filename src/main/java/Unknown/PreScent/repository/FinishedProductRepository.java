package Unknown.PreScent.repository;

import Unknown.PreScent.entity.FinishedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinishedProductRepository extends JpaRepository<FinishedProductEntity, Integer>
{
    Optional<FinishedProductEntity> findByFpKey(Integer fpKey);
    Optional<List<FinishedProductEntity>> findByFpNameContaining(String fpName);
    Optional<List<FinishedProductEntity>> findByFpTagContaining(String fpTag);
//    @Query("SELECT e FROM FinishedProduct e WHERE :flower  MEMBER OF e.fpFlowerList")
//    Optional<List<FinishedProduct>> findByValue(@Param("flower") String flower);
}
