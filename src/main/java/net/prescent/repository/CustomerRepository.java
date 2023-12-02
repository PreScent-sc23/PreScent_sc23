package net.prescent.repository;

import net.prescent.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    //CustomerDto save(CustomerDto seller);
    Optional<CustomerEntity> findByUserKey(Integer userKey);
    Optional<CustomerEntity> findByName(String name);
    Optional<CustomerEntity> findByIdEmail(String idEmail);
    Optional<CustomerEntity> findByPassword(String password);
    Optional<CustomerEntity> findByPhonenum(String phonenum);
    Optional<CustomerEntity> findByLocation(String location);
    //List<CustomerDto> findAll();
}