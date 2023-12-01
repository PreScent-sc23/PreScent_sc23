//package Unknown.PreScent.service;
//
//
//import net.prescent.dto.PreScent.SellerDto;
//import net.prescent.entity.PreScent.SellerEntity;
//import net.prescent.repository.PreScent.SellerRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.transaction.Transactional;
//
//import static net.prescent.entity.PreScent.SellerEntity.toSellerEntity;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class SellerServiceTest {
//
//    @Autowired
//    SellerService sellerService;
//    @Autowired
//    SellerRepository sellerRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @BeforeEach
//    public void setUp() {
//        sellerRepository.deleteAllInBatch();
//    }
//
//    public SellerDto createSellerDto(Integer sellerKey){
//        SellerDto sellerDto = new SellerDto();
//        sellerDto.setSellerKey(sellerKey);
//        sellerDto.setSellerName("suhyeon");
//        sellerDto.setSellerPhonenum("010-1111-2222");
//        sellerDto.setSellerIdEmail("ajou@gmail.com");
//        sellerDto.setSellerPassword("04prescent");
//        return sellerDto;
//    }
//
//    @Test
//    @DisplayName("판매자 회원가입 테스트")
//    public void signupSellerTest() {
//        SellerDto sellerDto = createSellerDto(123456789);
//        SellerDto savedSellerDto = sellerService.signup(sellerDto);
//
//        assertNotNull(savedSellerDto.getSellerKey());
//        SellerEntity savedSeller = sellerRepository.findBySellerKey(savedSellerDto.getSellerKey())
//                .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다."));
//        assertTrue(passwordEncoder.matches("04prescent", savedSeller.getSellerPassword()));
//    }
//
//    @Test
//    @DisplayName("중복 사업자번호 가입 테스트")
//    public void signupDuplicateSellerKeyTest() {
//        // Given
//        SellerDto seller1 = createSellerDto(123456789);
//        sellerService.signup(seller1);
//
//        // When & Then
//        SellerDto seller2 = createSellerDto(123456789);
//        seller2.setSellerIdEmail("newemail@gmail.com");
//        seller2.setSellerPhonenum("010-3333-4444");
//
//        assertThrows(IllegalStateException.class, () -> sellerService.signup(seller2));
//    }
//
//
//    /*
//    @Test
//    @DisplayName("판매자/ 중복 이메일 가입 테스트")
//    public void signupDuplicateEmailTest() {
//        SellerDto seller1 = createSellerDto();
//        sellerService.signup(seller1);
//
//        SellerDto seller2 = createSellerDto();
//        seller2.setSellerPhonenum("010-2222-3333");
//
//        Throwable e = assertThrows(IllegalStateException.class, () -> {
//            sellerService.signup(seller2);
//        });
//        assertEquals("이미 등록된 사업자입니다.", e.getMessage());
//    }
//     */
//
//    /*
//    @Test
//    @DisplayName("판매자/ 로그인 성공 테스트")
//    public void loginSuccessTest() {
//        SellerDto sellerDto = createSellerDto();
//        sellerDto.setSellerPassword(passwordEncoder.encode(sellerDto.getSellerPassword()));
//        Integer sellerKey = sellerService.signup(sellerDto);
//
//        SellerDto loggedInSeller = sellerService.login(sellerDto.getSellerIdEmail(), sellerDto.getSellerPassword());
//        assertNotNull(loggedInSeller);
//    }
//
//    @Test
//    @DisplayName("판매자/ 로그인 실패 - 잘못된 Email 테스트")
//    void loginFailureWrongIdTest() {
//        SellerDto newSeller = createSellerDto();
//        sellerService.signup(newSeller);
//
//        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
//            sellerService.login("wrongId", "04prescent");
//        });
//        assertEquals("존재하지 않는 사용자 Email입니다.", e.getMessage());
//    }
//
//    @Test
//    @DisplayName("판매자/ 로그인 실패 - 잘못된 PASSWORD 테스트")
//    void loginFailureWrongPasswordTest() {
//        SellerDto newSeller = createSellerDto();
//        sellerService.signup(newSeller);
//
//        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
//            sellerService.login("ajou.gmail.com", "wrongpassword");
//        });
//        assertEquals("비밀번호가 일치하지 않습니다.", e.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("sellerKey 이용한 seller 쿼리 테스트")
//    void sellerQueryWithSelleKeyTest()
//    {
//        SellerDto newSeller = createSellerDto();
//        sellerService.signup(newSeller);
//
//        assertTrue(sellerRepository.findBySellerKey(123456789).isPresent());
//    }
//    */
//}