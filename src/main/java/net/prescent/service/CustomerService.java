package net.prescent.service;

import net.prescent.dto.CustomerDto;
import net.prescent.entity.CustomerEntity;
import net.prescent.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;

    @Transactional
    public CustomerDto signup(CustomerDto customerDto) {
        validateDuplicatedCustomer(customerDto.getIdEmail());

        CustomerEntity customerEntity = CustomerEntity.toCustomerEntity(customerDto);
        customerEntity.setPassword(passwordEncoder.encode(customerDto.getPassword()));

        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return CustomerDto.toCustomerDto(savedEntity);
    }

    private void validateDuplicatedCustomer(String customerIdEmail) {
        customerRepository.findByIdEmail(customerIdEmail)
                .ifPresent(s -> {
                    throw new IllegalStateException("이미 등록된 회원입니다.");
                });
    }

    @Transactional
    public String login(String id, String password) {
        CustomerEntity customer = customerRepository.findByIdEmail(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 Email입니다."));

        if (passwordEncoder.matches(password, customer.getPassword())) {
            return accessTokenService.createAccessToken(customer);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
