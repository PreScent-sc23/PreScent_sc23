package net.prescent.service;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.*;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.repository.CartRepository;
import net.prescent.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
@Slf4j
public class CartServiceTest {
    @Autowired
    CartRepository CartRepo;
    @Autowired
    UserService userService;
    @Autowired
    FlowerShopService flowerShopService;
    @Autowired
    FinishedProductService finishedProductService;
    @Autowired
    CartService cartService;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    public SellerDto createSellerDto(Long businessKey) {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setBusinessKey(businessKey);
        sellerDto.setName("suhyeon");
        sellerDto.setPhonenum("010-1111-2222");
        sellerDto.setIdEmail("ajou@gmail.com");
        sellerDto.setPassword("04prescent");
        sellerDto.setConfirmPassword(("04prescent"));
        return sellerDto;
    }

    public FlowerShopDto createFlowerShopDto(){
        FlowerShopDto flowerShopDto = new FlowerShopDto();
        flowerShopDto.setBusinessKey(12345678L);
        flowerShopDto.setShopName("it's me");
        flowerShopDto.setShopPhoneNum("031-308-8223");
        flowerShopDto.setShopLocation("suwon city");
        flowerShopDto.setOpenHour(10);
        flowerShopDto.setOpenMinute(0);
        flowerShopDto.setCloseHour(20);
        flowerShopDto.setCloseMinute(0);
        flowerShopDto.setWorkday(new String[]{"월", "화", "수", "목", "금"});
        flowerShopDto.setDescription("안녕하세요 디스크립션 입니다.");
        return flowerShopDto;
    }

    public FinishedProductDto createFinishedProductDto() {
        FinishedProductDto finishedProductDto = new FinishedProductDto();
        finishedProductDto.setFpName("장미꽃다발");
        finishedProductDto.setFpTag("연인");
        finishedProductDto.setFpImage("https://prescentbucket.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20231123_203956157_01.png");
        finishedProductDto.setFpPrice(20000);
        finishedProductDto.setFpFlowerList("장미 안개꽃");
        return finishedProductDto;
    }
    private CustomerDto createTestCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("예시 구매자");
        customerDto.setIdEmail("buyer@naver.com");
        customerDto.setPassword("12345");
        customerDto.setConfirmPassword("12345");
        customerDto.setPhonenum("010-2222-3333");
        customerDto.setLocation("Test Location");
        return customerDto;
    }
    //테스트 목록. 주문서 작성.
    @Test
    @DisplayName("카트 추가 테스트")
    public void addInCartTest() {
        CustomerDto customerDto = createTestCustomerDto();
        Integer customerKey = userService.signupCustomer(customerDto);
        assertNotNull(customerKey);

        SellerDto sellerDto = createSellerDto(12345678L);
        Long businessKey = userService.signupSeller(sellerDto);

        FlowerShopDto flowerShopDto = createFlowerShopDto();
        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);

        FinishedProductDto finishedProductDto = createFinishedProductDto();
        finishedProductDto.setShopKey(addedShop.getShopKey());
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(finishedProductDto);

        cartService.addToCart(customerKey, addedFinishedProductEntity.getFpKey(), 1, "20231204", "18:00");
        assertNotNull(customerRepo.findByUserKey(customerKey).get().getCartEntity().getCartItemEntityList());
    }

    @Test
    @DisplayName("카트정보 조회 테스트")
    public void viewInCartTest() {
        CustomerDto customerDto = createTestCustomerDto();
        Integer customerKey = userService.signupCustomer(customerDto);
        assertNotNull(customerKey);

        SellerDto sellerDto = createSellerDto(12345678L);
        Long businessKey = userService.signupSeller(sellerDto);

        FlowerShopDto flowerShopDto = createFlowerShopDto();
        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);

        FinishedProductDto finishedProductDto = createFinishedProductDto();
        finishedProductDto.setShopKey(addedShop.getShopKey());
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(finishedProductDto);

        cartService.addToCart(customerKey, addedFinishedProductEntity.getFpKey(), 1, "20231204", "18:00");
        assertNotNull(customerRepo.findByUserKey(customerKey).get().getCartEntity().getCartItemEntityList());

        CartResponseDto cartResponseDto = cartService.viewInCart(customerKey);
        assertEquals(cartResponseDto.getCartItemResponseDtoList().get(0).getFlowerShopName(),flowerShopDto.getShopName());
        log.info(cartResponseDto.toString());

    }
}
