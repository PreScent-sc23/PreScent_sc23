package net.prescent.service;

import net.prescent.dto.CustomerDto;
import net.prescent.dto.SellerDto;
import net.prescent.entity.CustomerEntity;
import net.prescent.entity.SellerEntity;
import net.prescent.repository.CustomerRepository;
import net.prescent.repository.SellerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccessTokenService accessTokenService;

    private CustomerDto createTestCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Test Customer");
        customerDto.setIdEmail("sooh@ajou.ac.kr");
        customerDto.setPassword("password1");
        customerDto.setConfirmPassword("password1");
        customerDto.setPhonenum("010-1234-5678");
        customerDto.setAddress("Test Location");
        return customerDto;
    }

    private SellerDto createTestSellerDto() {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setBusinessKey(12345L);
        sellerDto.setName("Test Seller");
        sellerDto.setIdEmail("sooh@ajou.ac.kr");
        sellerDto.setPassword("password2");
        sellerDto.setConfirmPassword("password2");
        sellerDto.setPhonenum("010-9876-5432");
        return sellerDto;
    }

    @Test
    @DisplayName("고객 등록 테스트")
    public void testRegisterCustomer() {
        CustomerDto customerDto = createTestCustomerDto();
        userService.testSignupCustomer(customerDto);
        CustomerEntity registeredCustomer = customerRepository.findByIdEmail(customerDto.getIdEmail()).orElse(null);
        assertNotNull(registeredCustomer, "고객이 성공적으로 등록되었습니다.");
        assertTrue(passwordEncoder.matches("password1", registeredCustomer.getPassword()), "비밀번호가 정확히 암호화되었습니다.");
    }

    @Test
    @DisplayName("판매자 등록 테스트")
    public void testRegisterSeller() {
        SellerDto sellerDto = createTestSellerDto();
        userService.signupSeller(sellerDto);
        SellerEntity registeredSeller = sellerRepository.findByIdEmail(sellerDto.getIdEmail()).orElse(null);
        assertNotNull(registeredSeller, "판매자가 성공적으로 등록되었습니다.");
        assertTrue(passwordEncoder.matches("password2", registeredSeller.getPassword()), "비밀번호가 정확히 암호화되었습니다.");
    }

    @Test
    @DisplayName("등록된 판매자 테스트")
    public void testDuplicateSellerBusinessKeyRegistration() {
        SellerDto sellerDto1 = createTestSellerDto();
        userService.signupSeller(sellerDto1);

        SellerDto sellerDto2 = createTestSellerDto();
        sellerDto2.setIdEmail("suhyeon.k.official@gmail.com");

        assertThrows(IllegalStateException.class, () -> {
            userService.signupSeller(sellerDto2);
        }, "이미 등록된 사업자 번호로 가입을 시도하면 예외가 발생해야 합니다.");
    }

    @Test
    @DisplayName("등록된 고객 테스트")
    public void testDuplicateCustomerEmailRegistration() {
        CustomerDto customerDto1 = createTestCustomerDto();
        userService.testSignupCustomer(customerDto1);

        CustomerDto customerDto2 = createTestCustomerDto();
        customerDto2.setPhonenum("010-5678-1234");

        assertThrows(IllegalStateException.class, () -> {
            userService.signupCustomer(customerDto2);
        }, "이미 등록된 이메일로 고객 가입을 시도하면 예외가 발생해야 합니다.");
    }

    @Test
    @DisplayName("로그인 토큰 테스트")
    public void testLoginSuccess() {
        CustomerDto customerDto = createTestCustomerDto();
        userService.testSignupCustomer(customerDto);

        String token = userService.login("sooh@ajou.ac.kr", "password1");
        assertNotNull(token, "로그인에 성공하면 토큰이 반환되어야 합니다.");
        assertTrue(accessTokenService.validateAccessToken(token), "반환된 토큰은 유효해야 합니다.");
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void testLoginFailure() {
        CustomerDto customerDto = createTestCustomerDto();
        userService.testSignupCustomer(customerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.login("sooh@ajou.ac.kr", "wrongpassword");
        });
    }

    @Test
    @DisplayName("로그아웃 테스트")
    public void testLogout() {
        CustomerDto customerDto = createTestCustomerDto();
        userService.testSignupCustomer(customerDto);
        String token = userService.login("sooh@ajou.ac.kr", "password1");

        userService.logout(token);
        assertFalse(accessTokenService.validateAccessToken(token), "로그아웃 후에는 토큰이 무효화되어야 합니다.");
    }
}
