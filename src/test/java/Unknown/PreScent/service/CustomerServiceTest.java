package Unknown.PreScent.service;

import Unknown.PreScent.dto.CustomerDto;
import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.entity.CustomerEntity;
import Unknown.PreScent.entity.SellerEntity;
import Unknown.PreScent.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static Unknown.PreScent.entity.CustomerEntity.toCustomerEntity;
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

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAllInBatch();
    }

    public CustomerDto createCustomerDto(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerName("suhyeon");
        customerDto.setCustomerPhonenum("010-1111-2222");
        customerDto.setCustomerIdEmail("ajou.gmail.com");
        customerDto.setCustomerPassword("04prescent");
        return customerDto;
    }

    @Test
    @DisplayName("고객/ 회원가입 테스트")
    public void signupCustomerTest() {
        CustomerDto customerDto = createCustomerDto();
        CustomerDto savedCustomerDto = customerService.signup(customerDto);

        assertNotNull(savedCustomerDto.getCustomerIdEmail());
        CustomerEntity savedCustomer = customerRepository.findByCustomerIdEmail(savedCustomerDto.getCustomerIdEmail())
                .orElseThrow(() -> new IllegalArgumentException("고객를 찾을 수 없습니다."));
        assertTrue(passwordEncoder.matches("04prescent", savedCustomer.getCustomerPassword()));
    }

    @Test
    @DisplayName("고객/ 중복 이메일 가입 테스트")
    public void saveDuplicateCustomerTest(){
        CustomerDto customer1 = createCustomerDto();
        customerService.signup(customer1);

        CustomerDto customer2 = createCustomerDto();
        customer2.setCustomerIdEmail("newemail@gmail.com");
        customer2.setCustomerPhonenum("010-3333-4444");

        assertThrows(IllegalStateException.class, () -> customerService.signup(customer2));
    }

    /*
    @Test
    @DisplayName("중복 아이디 가입 테스트")
    public void signupDuplicateIdTest() {
        SellerDto seller1 = createSellerDto();
        SellerDto seller2 = createSellerDto();
        seller2.setSellerKey(987654321L);

        sellerService.saveSeller(toSellerEntity(seller1));
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            sellerService.saveSeller(toSellerEntity(seller2));});
        assertEquals("이미 사용중인 아이디입니다.", e.getMessage());
    }
    */
    /*
    @Test
    @DisplayName("고객/ 로그인 성공 테스트")
    public void loginSuccessTest() {
        CustomerDto customerDto = createCustomerDto();
        customerDto.setCustomerPassword(passwordEncoder.encode(customerDto.getCustomerPassword()));
        String customerIdEmail = customerService.signup(customerDto);

        CustomerDto loggedInCustomer = customerService.login(customerDto.getCustomerIdEmail(), customerDto.getCustomerPassword());
        assertNotNull(loggedInCustomer);
    }

    @Test
    @DisplayName("고객/ 로그인 실패 - 잘못된 Email 테스트")
    void loginFailureWrongIdTest() {
        CustomerDto newCustomer = createCustomerDto();
        customerService.signup(newCustomer);

        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            customerService.login("wrongId", "04prescent");
        });
        assertEquals("존재하지 않는 사용자 Email입니다.", e.getMessage());
    }

    @Test
    @DisplayName("고객/ 로그인 실패 - 잘못된 PASSWORD 테스트")
    void loginFailureWrongPasswordTest() {
        CustomerDto newCustomer = createCustomerDto();
        customerService.signup(newCustomer);

        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            customerService.login("ajou.gmail.com", "wrongpassword");
        });
        assertEquals("비밀번호가 일치하지 않습니다.", e.getMessage());
    }
     */
}
