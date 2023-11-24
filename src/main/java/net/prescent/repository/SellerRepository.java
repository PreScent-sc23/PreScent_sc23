package net.prescent.repository;

import net.prescent.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {

    //SellerDto save(SellerDto seller);
    Optional<SellerEntity> findBySellerKey(Integer sellerKey);
    Optional<SellerEntity> findByBusinessKey(Integer businessKey);
    Optional<SellerEntity> findBySellerName(String sellerName);
    Optional<SellerEntity> findBySellerIdEmail(String sellerIdEmail);
    Optional<SellerEntity> findBySellerPassword(String sellerPassword);
    Optional<SellerEntity> findBySellerPhonenum(String sellerPhonenum);
    Optional<SellerEntity> findByIsgrant(String sellerIsgrant);
    //List<SellerDto> findAll();

}
