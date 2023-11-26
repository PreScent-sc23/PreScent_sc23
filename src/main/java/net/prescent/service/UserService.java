package net.prescent.service;

import lombok.RequiredArgsConstructor;
import net.prescent.dto.CustomerDto;
import net.prescent.dto.SellerDto;
import net.prescent.entity.CustomerEntity;
import net.prescent.entity.SellerEntity;
import net.prescent.entity.UserEntity;
import net.prescent.repository.CustomerRepository;
import net.prescent.repository.SellerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;

    public void signupCustomer(CustomerDto customerDto) {
        verifyPasswordMatch(customerDto.getPassword(), customerDto.getConfirmPassword());
        verifyCustomerNotRegistered(customerDto.getIdEmail());
        CustomerEntity customer = CustomerEntity.toCustomerEntity(customerDto);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    public void signupSeller(SellerDto sellerDto) {
        verifyPasswordMatch(sellerDto.getPassword(), sellerDto.getConfirmPassword());
        verifySellerNotRegistered(sellerDto.getBusinessKey(), sellerDto.getIdEmail());
        SellerEntity seller = SellerEntity.toSellerEntity(sellerDto);
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        sellerRepository.save(seller);
    }

    private void verifyPasswordMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }
    }

    private void verifyCustomerNotRegistered(String idEmail) {
        customerRepository.findByIdEmail(idEmail).ifPresent(c -> {
            throw new IllegalStateException("이미 등록된 고객입니다.");
        });
    }

    private void verifySellerNotRegistered(Integer businessKey, String idEmail) {
        sellerRepository.findByBusinessKey(businessKey).ifPresent(s -> {
            throw new IllegalStateException("이미 등록된 사업자입니다.");
        });
        sellerRepository.findByIdEmail(idEmail).ifPresent(s -> {
            throw new IllegalStateException("이미 등록된 이메일입니다.");
        });
    }

    public String login(String idEmail, String password) {
        UserEntity user = findUserByEmail(idEmail);
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return accessTokenService.createAccessToken(user);
    }

    public void logout(String token) {
        accessTokenService.deleteAccessToken(token);
    }

    private UserEntity findUserByEmail(String idEmail) {
        return Stream.of(
                        customerRepository.findByIdEmail(idEmail).orElse(null),
                        sellerRepository.findByIdEmail(idEmail).orElse(null))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
}