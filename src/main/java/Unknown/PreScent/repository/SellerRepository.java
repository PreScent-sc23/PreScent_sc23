package Unknown.PreScent.repository;

import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, String> {

    //SellerDto save(SellerDto seller);
    Optional<SellerEntity> findBySellerKey(Long sellerKey);
    Optional<SellerEntity> findBySellerName(String sellerName);
    Optional<SellerEntity> findByID(String ID);
    Optional<SellerEntity> findByPassword(String password);
    Optional<SellerEntity> findBySellerPhoneNum(String sellerPhoneNum);
    //List<SellerDto> findAll();

}
