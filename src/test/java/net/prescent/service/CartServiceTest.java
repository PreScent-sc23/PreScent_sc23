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

import java.util.List;

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
    FPOrderService fpOrderService;
    @Autowired
    PasswordEncoder passwordEncoder;
    private Integer tempNumForTest = 6;
    private String  tempStringForTest = "test8";
    //    @BeforeEach
//    public void setup()
//    {
//        sellerRepository.deleteAllInBatch();
//        flowerShopRepository.deleteAllInBatch();
//    }
    public SellerDto createSellerDto(){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setBusinessKey(123456789L+tempNumForTest);
        sellerDto.setName("판매자"+tempStringForTest);
        sellerDto.setPhonenum("010-9014-2635"+tempStringForTest);
        sellerDto.setIdEmail("seller@ajou.ac.kr"+tempStringForTest);
        sellerDto.setPassword("04prescent"+tempStringForTest);
        sellerDto.setConfirmPassword(("04prescent"+tempStringForTest));
        return sellerDto;
    }
    public FlowerShopDto createFlowerShopDto(){
        FlowerShopDto flowerShopDto = new FlowerShopDto();
        flowerShopDto.setBusinessKey(123456789L+tempNumForTest);
        flowerShopDto.setShopName("it's me");
        flowerShopDto.setShopPhoneNum("000-111-2222");
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
        String[] arr = {"장미 안개꽃"};
        finishedProductDto.setFpFlowerList(arr);
        return finishedProductDto;
    }
    public FinishedProductDto createFinishedProductDto(String fpDetail, String fpImage, String[] fpFlowerList, String fpName, String fpTag, Integer fpPrice) {
        FinishedProductDto finishedProductDto = new FinishedProductDto();
        finishedProductDto.setFpDetail(fpDetail);
        finishedProductDto.setFpName(fpName);
        finishedProductDto.setFpTag(fpTag);
        finishedProductDto.setFpImage(fpImage);
        finishedProductDto.setFpPrice(fpPrice);
        finishedProductDto.setFpFlowerList(fpFlowerList);
        return finishedProductDto;
    }
    private CustomerDto createTestCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("예시 구매자"+tempStringForTest);
        customerDto.setIdEmail(tempStringForTest+"buyer@naver.com");
        customerDto.setPassword("12345");
        customerDto.setConfirmPassword("12345");
        customerDto.setPhonenum("010-2222-3333");
        customerDto.setLocation("Test Location");
        return customerDto;
    }
    //테스트 목록. 주문서 작성.
//    @Test
//    @DisplayName("카트 추가 테스트")
//    public void addInCartTest() {
//        CustomerDto customerDto = createTestCustomerDto();
//        Integer customerKey = userService.signupCustomer(customerDto);
//        assertNotNull(customerKey);
//
//        SellerDto sellerDto = createSellerDto();
//        Integer sellerKey = userService.signupSeller(sellerDto);
//
//        FlowerShopDto flowerShopDto = createFlowerShopDto();
//        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);
//
//        FinishedProductDto finishedProductDto = createFinishedProductDto();
//        finishedProductDto.setShopKey(addedShop.getShopKey());
//        assertNotNull(addedShop.getShopKey());
//        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(finishedProductDto);
//
//        CartItemAddRequestDto cartItemAddRequestDto = new CartItemAddRequestDto(customerKey, addedFinishedProductEntity.getFpKey(), 1, "20231204", "18:00");
//        cartService.addToCart(cartItemAddRequestDto);
//        assertNotNull(customerRepo.findByUserKey(customerKey).get().getCartEntity().getCartItemEntityList());
//    }
//
//    @Test
//    @DisplayName("카트정보 조회 테스트")
//    public void viewInCartTest() {
//        CustomerDto customerDto = createTestCustomerDto();
//        Integer customerKey = userService.signupCustomer(customerDto);
//        assertNotNull(customerKey);
//
//        SellerDto sellerDto = createSellerDto();
//        Integer sellerKey = userService.signupSeller(sellerDto);
//
//        FlowerShopDto flowerShopDto = createFlowerShopDto();
//        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);
//
//        FinishedProductDto finishedProductDto = createFinishedProductDto();
//        finishedProductDto.setShopKey(addedShop.getShopKey());
//        assertNotNull(addedShop.getShopKey());
//        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(finishedProductDto);
//
//        CartItemAddRequestDto cartItemAddRequestDto = new CartItemAddRequestDto(customerKey, addedFinishedProductEntity.getFpKey(), 1, "20231204", "18:00");
//
//        cartService.addToCart(cartItemAddRequestDto);
//        assertNotNull(customerRepo.findByUserKey(customerKey).get().getCartEntity().getCartItemEntityList());
//
//        List<CartItemResponseDto> cartItemResponseDtoList = cartService.viewInCart(customerKey);
//        assertEquals(cartItemResponseDtoList.get(0).getFlowerShopName(),flowerShopDto.getShopName());
//        log.info(cartItemResponseDtoList.toString());
//    }

    @Test
    @DisplayName("db추가")
    public void addToDB(){
        CustomerDto customerDto = createTestCustomerDto();
        Integer customerKey = userService.signupCustomer(customerDto);
        assertNotNull(customerKey);

        SellerDto sellerDto = createSellerDto();
        Integer sellerKey = userService.signupSeller(sellerDto);

        FlowerShopDto flowerShopDto = createFlowerShopDto();
        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);

        FinishedProductDto finishedProductDto0 = createFinishedProductDto("메리골드가 돋보이는 화이트데이 꽃다발입니다","https://prescentbucket.s3.ap-northeast-2.amazonaws.com/orange.jpg",new String[]{"메리골드", "라넌큘려스", "사탕수수"},"메리골드 다발","#화이트데이", 100);
        finishedProductDto0.setShopKey(addedShop.getShopKey());
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity0 = finishedProductService.addFinishedProduct(finishedProductDto0);

        FinishedProductDto finishedProductDto1 = createFinishedProductDto("색의 조화가 매력적인 꽃다발입니다","https://prescentbucket.s3.ap-northeast-2.amazonaws.com/fall.jpg",new String[]{"장미", "안개"},"라넌큘러스 다발","#화이트데이",100);
        finishedProductDto1.setShopKey(addedShop.getShopKey());
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity1 = finishedProductService.addFinishedProduct(finishedProductDto1);

        FinishedProductDto finishedProductDto2 = createFinishedProductDto("핑크라넌과 코스모스를 조합한 꽃다발 입니다","https://prescentbucket.s3.ap-northeast-2.amazonaws.com/pink.jpg",new String[]{"라넌", "코스모스"},"핑크라넌","#화이트데이",100);
        finishedProductDto2.setShopKey(addedShop.getShopKey());
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity2 = finishedProductService.addFinishedProduct(finishedProductDto2);

        FinishedProductDto finishedProductDto3 = createFinishedProductDto("봄의 화사함을 담은 화이트데이 꽃다발입니다","https://prescentbucket.s3.ap-northeast-2.amazonaws.com/spring.jpg", new String[]{"개나리", "진달래"},"스프링 델피늄","#화이트데이",100);
        finishedProductDto3.setShopKey(addedShop.getShopKey());
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity3 = finishedProductService.addFinishedProduct(finishedProductDto3);

        FinishedProductDto finishedProductDto4 = createFinishedProductDto("미니 델피늄이 한가득 담긴 꽃다발입니다","https://prescentbucket.s3.ap-northeast-2.amazonaws.com/winter.jpg", new String[]{"델피늄", "한가득"},"델피늄 한가득","#화이트데이",100);
        finishedProductDto4.setShopKey(addedShop.getShopKey());
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity4 = finishedProductService.addFinishedProduct(finishedProductDto4);

        FinishedProductDto finishedProductDto5 = createFinishedProductDto("곰인형과 화이트로즈로 구성된 화이트데이 꽃다발","https://prescentbucket.s3.ap-northeast-2.amazonaws.com/lovely.png",new String[]{"장미", "라넌큘러스"},"러블리 꽃다발","#화이트데이",100);
        finishedProductDto5.setShopKey(addedShop.getShopKey());
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity5 = finishedProductService.addFinishedProduct(finishedProductDto5);

        CartItemAddRequestDto cartItemAddRequestDto = new CartItemAddRequestDto(customerKey, addedFinishedProductEntity0.getFpKey(), 1, "20231204", "18:00");
        cartService.addToCart(cartItemAddRequestDto);
        CartItemAddRequestDto cartItemAddRequestDto1 = new CartItemAddRequestDto(customerKey, addedFinishedProductEntity1.getFpKey(), 1, "20231204", "18:00");
        cartService.addToCart(cartItemAddRequestDto1);
        CartItemAddRequestDto cartItemAddRequestDto2 = new CartItemAddRequestDto(customerKey, addedFinishedProductEntity2.getFpKey(), 1, "20231204", "18:00");
        cartService.addToCart(cartItemAddRequestDto2);
        assertNotNull(customerRepo.findByUserKey(customerKey).get().getCartEntity().getCartItemEntityList());

        List<CartItemResponseDto> cartItemResponseDtoList = cartService.viewInCart(customerKey);
        assertEquals(cartItemResponseDtoList.get(0).getFlowerShopName(),flowerShopDto.getShopName());
        log.info(cartItemResponseDtoList.toString());

        fpOrderService.customerOrderInCart(customerKey,"테스트 결제입니다.");
    }
}
