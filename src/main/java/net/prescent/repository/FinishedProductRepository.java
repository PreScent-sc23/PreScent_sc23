package net.prescent.repository;

import net.prescent.entity.FinishedProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FinishedProductRepository extends JpaRepository<FinishedProductEntity, Integer>
{
    Optional<FinishedProductEntity> findByFpKey(Integer fpKey);
    Optional<FinishedProductEntity> findByFpKey(Integer fpKey, Sort sort);
    Optional<List<FinishedProductEntity>> findByFpNameContaining(String fpName);
    //Optional<List<FinishedProductEntity>> findByShopKey(Integer shopKey); //Not working
    Optional<List<FinishedProductEntity>> findByFpTagContaining(String fpTag);
    Optional<List<FinishedProductEntity>> findByFpTagContaining(String fpTag, Sort sort);
    Page<Optional<FinishedProductEntity>> findByFpTagContaining(String fpTag, Pageable pageable);
//    @Query("SELECT e FROM FinishedProduct e WHERE :flower  MEMBER OF e.fpFlowerList")
//    Optional<List<FinishedProduct>> findByValue(@Param("flower") String flower);
//    @Query(value = "SELECT p" +
//            "FROM FinishedProductEntity p " +
//            "JOIN p.FlowerShop s " +
//            "WHERE ST_DISTANCE(POINT(:customerLongitude, :customerLatitude), POINT(s.longitude, s.latitude)) <= :maxDistance " +
//            "AND p.FpName = :fpName " +
//            "ORDER BY distance")
//    List<FinishedProductEntity> searchProductsByLocation(
//            @Param("customerLongitude") Double customerLongitude,
//            @Param("customerLatitude") Double customerLatitude,
//            @Param("maxDistance") Double maxDistance,
//            @Param("fpName") String fpName);
}
