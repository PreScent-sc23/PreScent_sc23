package Unknown.PreScent.service;


import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

import static Unknown.PreScent.entity.SellerEntity.toSellerEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
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

    public SellerDto createSellerDto(){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setSellerKey(123456789);
        sellerDto.setSellerName("suhyeon");
        sellerDto.setSellerPhonenum("010-1111-2222");
        sellerDto.setSellerId("sooh");
        sellerDto.setSellerPassword("04prescent");
        return sellerDto;
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void signupSellerTest() {
        SellerDto sellerDto = createSellerDto();
        Integer sellerKey = sellerService.signup(sellerDto);
        assertNotNull(sellerKey);
    }

    @Test
    @DisplayName("중복 사업자번호 가입 테스트")
    public void saveDuplicateSellerTest(){
        SellerDto seller1 = createSellerDto();
        SellerDto seller2 = createSellerDto();
        seller2.setSellerId("differentId");

        sellerService.saveSeller(toSellerEntity(seller1));
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            sellerService.saveSeller(toSellerEntity(seller2));});
        assertEquals("이미 등록된 사업자입니다.", e.getMessage());
    }

    @Test
    @DisplayName("중복 아이디 가입 테스트")
    public void signupDuplicateIdTest() {
        SellerDto seller1 = createSellerDto();
        SellerDto seller2 = createSellerDto();
        seller2.setSellerKey(987654321);

        sellerService.saveSeller(toSellerEntity(seller1));
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            sellerService.saveSeller(toSellerEntity(seller2));});
        assertEquals("이미 사용중인 아이디입니다.", e.getMessage());
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() {
        SellerDto sellerDto = createSellerDto();
        sellerDto.setSellerPassword(passwordEncoder.encode(sellerDto.getSellerPassword()));
        Integer sellerKey = sellerService.signup(sellerDto);

        SellerDto loggedInSeller = sellerService.login(sellerDto.getSellerId(), sellerDto.getSellerPassword());
        assertNotNull(loggedInSeller);
    }

    @Test
    @DisplayName("로그인 실패 - 잘못된 ID 테스트")
    void loginFailureWrongIdTest() {
        SellerDto newSeller = createSellerDto();
        sellerService.signup(newSeller);

        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            sellerService.login("wrongId", "04prescent");
        });
        assertEquals("존재하지 않는 사용자 ID입니다.", e.getMessage());
    }

    @Test
    @DisplayName("로그인 실패 - 잘못된 PASSWORD 테스트")
    void loginFailureWrongPasswordTest() {
        SellerDto newSeller = createSellerDto();
        sellerService.signup(newSeller);

        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            sellerService.login("sooh", "wrongpassword");
        });
        assertEquals("비밀번호가 일치하지 않습니다.", e.getMessage());
    }

    @Test
    @DisplayName("sellerKey를 이용한 seller쿼리 테스트")
    void sellerQueryWithSellerKeyTest() {
        SellerDto newSeller = createSellerDto();
        sellerService.signup(newSeller);

        assertTrue(sellerRepository.findBySellerKey(123456789).isPresent());
    }
}