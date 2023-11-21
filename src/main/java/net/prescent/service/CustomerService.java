package net.prescent.service;

import net.prescent.dto.CustomerDto;
import net.prescent.entity.CustomerEntity;
import net.prescent.repository.CustomerRepository;
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
    public CustomerDto signup(CustomerDto customerDto){
        validateDuplicatedCustomer(customerDto.getCustomerIdEmail());

        CustomerEntity customerEntity = CustomerEntity.toCustomerEntity(customerDto);
        customerEntity.setCustomerPassword(passwordEncoder.encode(customerDto.getCustomerPassword()));

        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return CustomerDto.toCustomerDto(savedEntity);
    }

    private void validateDuplicatedCustomer(String customerIdEmail) {
        customerRepository.findByCustomerIdEmail(customerIdEmail)
                .ifPresent(s ->{
                    throw new IllegalStateException("이미 등록된 회원입니다.");
                });
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
