package Unknown.PreScent.service;


import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.entity.SellerEntity;
import Unknown.PreScent.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.mock.web.MockHttpSession;

import javax.transaction.Transactional;

import static Unknown.PreScent.entity.SellerEntity.toSellerEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SellerServiceTest {

    @Autowired
    SellerService sellerService;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        sellerRepository.deleteAllInBatch();
    }

    private SellerDto createSellerDto(Integer sellerKey, String email) {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setSellerKey(sellerKey);
        sellerDto.setSellerName("suhyeon");
        sellerDto.setSellerPhonenum("010-1111-2222");
        sellerDto.setSellerIdEmail(email);
        sellerDto.setSellerPassword("04prescent");
        return sellerDto;
    }

    @Test
    @DisplayName("판매자 회원가입 테스트")
    public void signupSellerTest() {
        SellerDto sellerDto = createSellerDto(123456789, "ajou@gmail.com");
        SellerDto savedSellerDto = sellerService.signup(sellerDto);

        assertNotNull(savedSellerDto.getSellerKey());
        SellerEntity savedSeller = sellerRepository.findBySellerIdEmail(savedSellerDto.getSellerIdEmail())
                .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다."));
        assertTrue(passwordEncoder.matches("04prescent", savedSeller.getSellerPassword()));
    }

    @Test
    @DisplayName("중복 사업자번호 가입 테스트")
    public void signupDuplicateSellerKeyTest() {
        SellerDto seller1 = createSellerDto(123456789, "ajou@gmail.com");
        sellerService.signup(seller1);

        SellerDto seller2 = createSellerDto(123456789, "newemail@gmail.com");

        assertThrows(IllegalStateException.class, () -> sellerService.signup(seller2));
    }

    @Test
    @DisplayName("판매자 로그인 성공 테스트")
    public void loginSuccessTest() {
        MockHttpSession session = new MockHttpSession();
        SellerDto sellerDto = createSellerDto(123456789, "ajou@gmail.com");
        sellerService.signup(sellerDto);

        SellerDto loggedInSeller = sellerService.login("ajou@gmail.com", "04prescent", session);
        assertNotNull(loggedInSeller);
    }

    @Test
    @DisplayName("판매자 로그인 실패 - 잘못된 Email 테스트")
    void loginFailureWrongIdTest() {
        MockHttpSession session = new MockHttpSession();
        SellerDto sellerDto = createSellerDto(123456789, "ajou@gmail.com");
        sellerService.signup(sellerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            sellerService.login("wrongemail@gmail.com", "04prescent", session);
        });
    }

    @Test
    @DisplayName("판매자 로그인 실패 - 잘못된 비밀번호 테스트")
    void loginFailureWrongPasswordTest() {
        MockHttpSession session = new MockHttpSession();
        SellerDto sellerDto = createSellerDto(123456789, "ajou@gmail.com");
        sellerService.signup(sellerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            sellerService.login("ajou@gmail.com", "wrongpassword", session);
        });
    }


    /*
    @Test
    @DisplayName("sellerKey 이용한 seller 쿼리 테스트")
    void sellerQueryWithSelleKeyTest()
    {
        SellerDto newSeller = createSellerDto();
        sellerService.signup(newSeller);

        assertTrue(sellerRepository.findBySellerKey(123456789).isPresent());
    }
    */
}