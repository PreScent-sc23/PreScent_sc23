package Unknown.PreScent.repository;

import Unknown.PreScent.dto.SellerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerDto, String> {

    /*
    SellerDto save(SellerDto seller);
    Optional<SellerDto> findBySellerKey(Long sellerKey);
    Optional<SellerDto> findBySellerName(String sellerName);
    Optional<SellerDto> findByID(String ID);
    Optional<SellerDto> findByPassword(String password);
    Optional<SellerDto> findBySellerPhoneNum(String sellerPhoneNum);
    List<SellerDto> findAll();

     */
}
