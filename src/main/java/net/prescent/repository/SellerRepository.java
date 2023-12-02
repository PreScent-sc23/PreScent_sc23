package net.prescent.repository;

import net.prescent.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {

    //SellerDto save(SellerDto seller);
    Optional<SellerEntity> findByUserKey(Long userKey);
    Optional<SellerEntity> findByBusinessKey(Long businessKey);
    Optional<SellerEntity> findByName(String name);
    Optional<SellerEntity> findByIdEmail(String idEmail);
    Optional<SellerEntity> findByPassword(String password);
    Optional<SellerEntity> findByPhonenum(String phonenum);
    Optional<SellerEntity> findByIsgrant(String isgrant);
    //List<SellerDto> findAll();

}
