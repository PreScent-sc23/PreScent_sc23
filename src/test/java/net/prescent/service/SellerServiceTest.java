package net.prescent.service;


import net.prescent.dto.SellerDto;
import net.prescent.entity.SellerEntity;
import net.prescent.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.mock.web.MockHttpSession;

import javax.transaction.Transactional;

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
    @Autowired
    AccessTokenService accessTokenService;  // 토큰 서비스 추가

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
        SellerDto sellerDto = createSellerDto(123456789, "ajou@gmail.com");
        sellerService.signup(sellerDto);

        String token = sellerService.login("ajou@gmail.com", "04prescent");
        assertNotNull(token, "토큰이 반환되지 않았습니다.");

        boolean isValidToken = accessTokenService.validateAccessToken(token);
        assertTrue(isValidToken, "토큰이 유효하지 않습니다.");
    }

    @Test
    @DisplayName("판매자 로그인 실패 - 잘못된 Email 테스트")
    void loginFailureWrongIdTest() {
        SellerDto sellerDto = createSellerDto(123456789, "ajou@gmail.com");
        sellerService.signup(sellerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            sellerService.login("wrongemail@gmail.com", "04prescent");
        });
    }

    @Test
    @DisplayName("판매자 로그인 실패 - 잘못된 비밀번호 테스트")
    void loginFailureWrongPasswordTest() {
        SellerDto sellerDto = createSellerDto(123456789, "ajou@gmail.com");
        sellerService.signup(sellerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            sellerService.login("ajou@gmail.com", "wrongpassword");
        });
    }

    @Test
    @DisplayName("판매자 로그아웃 테스트")
    public void logoutTest() {
        SellerDto sellerDto = createSellerDto(123456789, "ajou@gmail.com");
        sellerService.signup(sellerDto);
        String token = sellerService.login("ajou@gmail.com", "04prescent");

        boolean isValidTokenBeforeLogout = accessTokenService.validateAccessToken(token);
        assertTrue(isValidTokenBeforeLogout, "로그아웃 전 토큰이 유효해야 합니다.");

        accessTokenService.deleteAccessToken(token);

        boolean isValidTokenAfterLogout = accessTokenService.validateAccessToken(token);
        assertFalse(isValidTokenAfterLogout, "로그아웃 후 토큰은 유효하지 않아야 합니다.");
    }

}