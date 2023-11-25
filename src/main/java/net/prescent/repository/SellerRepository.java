package net.prescent.repository;

import net.prescent.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {

    //SellerDto save(SellerDto seller);
    Optional<SellerEntity> findByUserKey(Integer userKey);
    Optional<SellerEntity> findByBusinessKey(Integer businessKey);
    Optional<SellerEntity> findByName(String name);
    Optional<SellerEntity> findByIdEmail(String idEmail);
    Optional<SellerEntity> findByPassword(String password);
    Optional<SellerEntity> findByPhonenum(String phonenum);
    Optional<SellerEntity> findByIsgrant(String isgrant);
    //List<SellerDto> findAll();

}
