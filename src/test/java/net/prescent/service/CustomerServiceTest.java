package net.prescent.service;

import net.prescent.dto.CustomerDto;
import net.prescent.entity.CustomerEntity;
import net.prescent.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccessTokenService accessTokenService;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAllInBatch();
    }

    private CustomerDto createCustomerDto() {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setName("고객");
        customerDto.setPhonenum("010-1234-1234");
        customerDto.setIdEmail("customerTest@gmail.com");
        customerDto.setPassword("04customer");
        return customerDto;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("고객 회원가입 테스트")
    public void signupCustomerTest() {
        CustomerDto customerDto = createCustomerDto();
        CustomerDto savedCustomerDto = customerService.signup(customerDto);

        assertNotNull(customerRepository.findByUserKey(savedCustomerDto.getUserKey()));
        CustomerEntity savedCustomer = customerRepository.findByIdEmail(savedCustomerDto.getIdEmail())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        assertTrue(passwordEncoder.matches(customerDto.getPassword(), savedCustomer.getPassword()));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("중복 이메일 가입 테스트")
    public void signupDuplicateCustomerIdEmailTest() {
        CustomerDto customer1 = createCustomerDto();
        customerService.signup(customer1);

        CustomerDto customer2 = createCustomerDto();
        customer2.setPassword("04prescent");

        assertThrows(IllegalStateException.class, () -> customerService.signup(customer2));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("고객 로그인 성공 테스트")
    public void loginSuccessTest() {
        CustomerDto customerDto = createCustomerDto();
        customerService.signup(customerDto);

        String token = customerService.login(customerDto.getIdEmail(), customerDto.getPassword());
        assertNotNull(token, "토큰이 반환되지 않았습니다.");

        boolean isValidToken = accessTokenService.validateAccessToken(token);
        assertTrue(isValidToken, "토큰이 유효하지 않습니다.");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("고객 로그인 실패 - 잘못된 Email 테스트")
    void loginFailureWrongIdTest() {
        CustomerDto customerDto = createCustomerDto();
        customerService.signup(customerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            customerService.login("wrongemail@gmail.com", customerDto.getPassword());
        });
    }

    @org.junit.jupiter.api.Test
    @DisplayName("고객 로그인 실패 - 잘못된 비밀번호 테스트")
    void loginFailureWrongPasswordTest() {
        CustomerDto customerDto = createCustomerDto();
        customerService.signup(customerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            customerService.login(customerDto.getIdEmail(), "wrongpassword");
        });
    }

    @org.junit.jupiter.api.Test
    @DisplayName("고객 로그아웃 테스트")
    public void logoutTest() {
        CustomerDto customerDto = createCustomerDto();
        customerService.signup(customerDto);
        String token = customerService.login(customerDto.getIdEmail(), customerDto.getPassword());

        boolean isValidTokenBeforeLogout = accessTokenService.validateAccessToken(token);
        assertTrue(isValidTokenBeforeLogout, "로그아웃 전 토큰이 유효해야 합니다.");

        accessTokenService.deleteAccessToken(token);

        boolean isValidTokenAfterLogout = accessTokenService.validateAccessToken(token);
        assertFalse(isValidTokenAfterLogout, "로그아웃 후 토큰은 유효하지 않아야 합니다.");
    }
}
