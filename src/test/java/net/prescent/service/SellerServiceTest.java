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
    AccessTokenService accessTokenService;

    @BeforeEach
    public void setUp() {
        sellerRepository.deleteAllInBatch();
    }

    private SellerDto createSellerDto() {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setBusinessKey(1234512345);
        sellerDto.setSellerName("사업자");
        sellerDto.setSellerPhonenum("010-5678-5678");
        sellerDto.setSellerIdEmail("sellerTest@gmail.com");
        sellerDto.setSellerPassword("04seller");
        return sellerDto;
    }

    @Test
    @DisplayName("판매자 회원가입 테스트")
    public void signupSellerTest() {
        SellerDto sellerDto = createSellerDto();
        SellerDto savedSellerDto = sellerService.signup(sellerDto);

        assertNotNull(savedSellerDto.getBusinessKey());
        SellerEntity savedSeller = sellerRepository.findBySellerIdEmail(savedSellerDto.getSellerIdEmail())
                .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다."));
        assertTrue(passwordEncoder.matches(sellerDto.getSellerPassword(), savedSeller.getSellerPassword()));
    }

    @Test
    @DisplayName("중복 사업자번호 가입 테스트")
    public void signupDuplicateBusinessKeyTest() {
        SellerDto seller1 = createSellerDto();
        sellerService.signup(seller1);

        SellerDto seller2 = createSellerDto();
        seller2.setSellerIdEmail("newemail@gmail.com");

        assertThrows(IllegalStateException.class, () -> sellerService.signup(seller2));
    }

    @Test
    @DisplayName("판매자 로그인 성공 테스트")
    public void loginSuccessTest() {
        SellerDto sellerDto = createSellerDto();
        sellerService.signup(sellerDto);

        String token = sellerService.login(sellerDto.getSellerIdEmail(), sellerDto.getSellerPassword());
        assertNotNull(token, "토큰이 반환되지 않았습니다.");

        boolean isValidToken = accessTokenService.validateAccessToken(token);
        assertTrue(isValidToken, "토큰이 유효하지 않습니다.");
    }

    @Test
    @DisplayName("판매자 로그인 실패 - 잘못된 Email 테스트")
    void loginFailureWrongIdTest() {
        SellerDto sellerDto = createSellerDto();
        sellerService.signup(sellerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            sellerService.login("wrongemail@gmail.com", sellerDto.getSellerPassword());
        });
    }

    @Test
    @DisplayName("판매자 로그인 실패 - 잘못된 비밀번호 테스트")
    void loginFailureWrongPasswordTest() {
        SellerDto sellerDto = createSellerDto();
        sellerService.signup(sellerDto);

        assertThrows(IllegalArgumentException.class, () -> {
            sellerService.login(sellerDto.getSellerIdEmail(), "wrongpassword");
        });
    }

    @Test
    @DisplayName("판매자 로그아웃 테스트")
    public void logoutTest() {
        SellerDto sellerDto = createSellerDto();
        sellerService.signup(sellerDto);
        String token = sellerService.login(sellerDto.getSellerIdEmail(), sellerDto.getSellerPassword());

        boolean isValidTokenBeforeLogout = accessTokenService.validateAccessToken(token);
        assertTrue(isValidTokenBeforeLogout, "로그아웃 전 토큰이 유효해야 합니다.");

        accessTokenService.deleteAccessToken(token);

        boolean isValidTokenAfterLogout = accessTokenService.validateAccessToken(token);
        assertFalse(isValidTokenAfterLogout, "로그아웃 후 토큰은 유효하지 않아야 합니다.");
    }

}