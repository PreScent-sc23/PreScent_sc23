package Unknown.PreScent.repository;

import Unknown.PreScent.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    //CustomerDto save(CustomerDto seller);
    Optional<CustomerEntity> findByCustomerKey(Long customerKey);
    Optional<CustomerEntity> findByCustomerName(String customerName);
    Optional<CustomerEntity> findByCustomerIdEmail(String customerIdEmail);
    Optional<CustomerEntity> findByCustomerPassword(String customerPassword);
    Optional<CustomerEntity> findByCustomerPhonenum(String customerPhonenum);
    Optional<CustomerEntity> findByCustomerLocation(String customerLocation);
    //List<CustomerDto> findAll();
}