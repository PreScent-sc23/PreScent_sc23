package Unknown.PreScent.service;

import Unknown.PreScent.dto.CustomerDto;
import Unknown.PreScent.entity.CustomerEntity;
import Unknown.PreScent.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signup(CustomerDto customerDto){
        CustomerEntity customerEntity = CustomerEntity.toCustomerEntity(customerDto);
        validateDuplicatedCustomer(customerEntity);

        String encoded;
        encoded = passwordEncoder.encode(customerEntity.getCustomerPassword());
        customerEntity.setCustomerPassword(encoded);

        customerRepository.save(customerEntity);
        return customerEntity.getCustomerIdEmail();
    }

    public Integer signupForTest(CustomerDto customerDto){
        CustomerEntity customerEntity = CustomerEntity.toCustomerEntity(customerDto);
        validateDuplicatedCustomer(customerEntity);

        String encoded;
        encoded = passwordEncoder.encode(customerEntity.getCustomerPassword());
        customerEntity.setCustomerPassword(encoded);

        customerRepository.save(customerEntity);
        return customerEntity.getCustomerKey();
    }

    private void validateDuplicatedCustomer(CustomerEntity customer) {
        customerRepository.findByCustomerIdEmail(customer.getCustomerIdEmail())
                .ifPresent(s ->{
                    throw new IllegalStateException("이미 등록된 회원입니다.");
                });
    }

    public CustomerEntity saveCustomer(CustomerEntity customerEntity){
        validateDuplicatedCustomer(customerEntity);
        return customerRepository.save(customerEntity);
    }

    public CustomerDto login(String id, String password) {

        Optional<CustomerEntity> byId = customerRepository.findByCustomerIdEmail(id);
        if (byId.isPresent()) {
            CustomerEntity customer = byId.get();
            if (passwordEncoder.matches(password, customer.getCustomerPassword())) {
                return CustomerDto.toCustomerDto(customer);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("존재하지 않는 사용자 Email입니다.");
        }
    }
}
