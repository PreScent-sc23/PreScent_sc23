//package net.prescent.service;
//
//import net.prescent.dto.*;
//import net.prescent.entity.FinishedProductEntity;
//import net.prescent.entity.FlowerShopEntity;
//
//import net.prescent.repository.FPOrderRepository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//import javax.transaction.Transactional;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@Transactional
//@SpringBootTest
//public class FPOrderServiceTest {
//    @Autowired
//    FPOrderRepository fpOrderRepo;
//    @Autowired
//    UserService userService;
//    @Autowired
//    FlowerShopService flowerShopService;
//    @Autowired
//    FinishedProductService finishedProductService;
//    @Autowired
//    FPOrderService fpOrderService;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    private Integer tempNumForTest = 1;
//    private String  tempStringForTest = "1";
//    //    @BeforeEach
////    public void setup()
////    {
////        sellerRepository.deleteAllInBatch();
////        flowerShopRepository.deleteAllInBatch();
////    }
//
//    public SellerDto createSellerDto(){
//        SellerDto sellerDto = new SellerDto();
//        sellerDto.setBusinessKey(1234567890L+tempNumForTest);
//        sellerDto.setName("kimkmim"+tempStringForTest);
//        sellerDto.setPhonenum("010-1111-222"+tempStringForTest);
//        sellerDto.setIdEmail("sooh"+tempStringForTest);
//        sellerDto.setPassword("04prescent"+tempStringForTest);
//        sellerDto.setConfirmPassword(("04prescent"+tempStringForTest));
//        return sellerDto;
//    }
//    public FlowerShopDto createFlowerShopDto(){
//        FlowerShopDto flowerShopDto = new FlowerShopDto();
//        flowerShopDto.setBusinessKey(1234567890L+tempNumForTest);
//        flowerShopDto.setShopName("it's me");
//        flowerShopDto.setShopPhoneNum("031-308-8223");
//        flowerShopDto.setShopLocation("suwon city");
//        flowerShopDto.setOpenHour(10);
//        flowerShopDto.setOpenMinute(0);
//        flowerShopDto.setCloseHour(20);
//        flowerShopDto.setCloseMinute(0);
//        flowerShopDto.setWorkday(new String[]{"월", "화", "수", "목", "금"});
//        flowerShopDto.setDescription("안녕하세요 디스크립션 입니다.");
//        return flowerShopDto;
//    }
//    public FinishedProductDto createFinishedProductDto() {
//        FinishedProductDto finishedProductDto = new FinishedProductDto();
//        finishedProductDto.setFpName("장미꽃다발");
//        finishedProductDto.setFpTag("연인");
//        finishedProductDto.setFpImage("https://prescentbucket.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20231123_203956157_01.png");
//        finishedProductDto.setFpPrice(20000);
//        String arr = "장미 안개꽃";
//        finishedProductDto.setGetFpFlowerList(arr);
//        return finishedProductDto;
//    }
//    public SellerDto createSeller1Dto() {
//        SellerDto sellerDto = new SellerDto();
//        sellerDto.setBusinessKey(111111111L+tempNumForTest);
//        sellerDto.setName("예시 사업자");
//        sellerDto.setPhonenum("010-1111-2222");
//        sellerDto.setIdEmail("seller@naver.com"+tempStringForTest);
//        sellerDto.setPassword("1234");
//        sellerDto.setConfirmPassword(("1234"));
//        return sellerDto;
//    }
//
//    public FlowerShopDto createFlowerShop1Dto(){
//        FlowerShopDto flowerShopDto = new FlowerShopDto();
//        flowerShopDto.setBusinessKey(111111111L+tempNumForTest);
//        flowerShopDto.setShopName("예시 꽃집");
//        flowerShopDto.setShopPhoneNum("031-111-2222");
//        flowerShopDto.setShopLocation("suwon city");
//        flowerShopDto.setOpenHour(10);
//        flowerShopDto.setOpenMinute(0);
//        flowerShopDto.setCloseHour(20);
//        flowerShopDto.setCloseMinute(0);
//        flowerShopDto.setWorkday(new String[]{"월", "화", "수", "목", "금"});
//        flowerShopDto.setDescription("예시 디스크립션 입니다.");
//        return flowerShopDto;
//    }
//
//    public FinishedProductDto createFinishedProduct1Dto() {
//        FinishedProductDto finishedProductDto = new FinishedProductDto();
//        finishedProductDto.setFpName("리시안셔스");
//        finishedProductDto.setFpTag("고백");
//        finishedProductDto.setFpImage("https://prescentbucket.s3.ap-northeast-2.amazonaws.com/%EC%98%88%EC%A0%9C+%EA%BD%832.jpeg");
//        finishedProductDto.setFpPrice(20000);
//        String[] arr = {"장미 안개꽃"};
//        finishedProductDto.setFpFlowerList(arr);
//        return finishedProductDto;
//    }
////
////    public CustomerDto createCustomerDto() {
////        CustomerDto customerDto = new CustomerDto();
////        customerDto.setName("suhyeon");
////        customerDto.setPhonenum("010-1111-2222");
////        customerDto.setIdEmail("ajou.gmail.com");
////        customerDto.setPassword("04prescent");
////        customerDto.setConfirmPassword(("04prescent"));
////        return customerDto;
////    }
//
//    private CustomerDto createTestCustomerDto() {
//        CustomerDto customerDto = new CustomerDto();
//        customerDto.setName("예시 구매자"+tempStringForTest);
//        customerDto.setIdEmail(tempStringForTest+"buyer@naver.com");
//        customerDto.setPassword("12345");
//        customerDto.setConfirmPassword("12345");
//        customerDto.setPhonenum("010-2222-3333");
//        customerDto.setLocation("Test Location");
//        return customerDto;
//    }
//
//    public FPOrderCustomerDto createFPOrderCustomerDto(Integer fpKey, Integer customerKey,String purchaseInfo, String pickupDate, String pickupTime)
//    {
//        FPOrderCustomerDto fpOrderCustomerDto = new FPOrderCustomerDto();
//        fpOrderCustomerDto.setFpKey(fpKey);
//        fpOrderCustomerDto.setCustomerKey(customerKey);
//        fpOrderCustomerDto.setPurchaseInfo(purchaseInfo);
//        fpOrderCustomerDto.setPickupDate(pickupDate);
//        fpOrderCustomerDto.setPickupTime(pickupTime);
//        fpOrderCustomerDto.setCount(1);
//        return fpOrderCustomerDto;
//    }
//    //테스트 목록. 주문서 작성.
//    @Test
//    @DisplayName("고객 완제품 주문 테스트 & 주문내역 조회 테스트")
//    public void customerFPOrderTest()
//    {
//        CustomerDto customerDto = createTestCustomerDto();
//        Integer customerKey = userService.signupCustomer(customerDto);
//        assertNotNull(customerKey);
//
//        SellerDto sellerDto = createSellerDto();
//        Integer sellerKey0 = userService.signupSeller(sellerDto);
//
//        FlowerShopDto flowerShopDto = createFlowerShopDto();
//        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);
//
//        FinishedProductDto finishedProductDto = createFinishedProductDto();
//        finishedProductDto.setShopKey(addedShop.getShopKey());
//        assertNotNull(addedShop.getShopKey());
//        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(finishedProductDto);
//
//        FPOrderCustomerDto fpOrderCustomerDto = createFPOrderCustomerDto(addedFinishedProductEntity.getFpKey(), customerKey, "국민카드 결제정보","20231203", "1800");
//        assertEquals(fpOrderService.customerOrderFinishedProduct(fpOrderCustomerDto).getPickupDate(),"20231203");
//
//
//        SellerDto anotherSellerDto = createSeller1Dto();
//        Integer sellerKey1 = userService.signupSeller(anotherSellerDto);
//
//        FlowerShopDto anotherFlowerShopDto = createFlowerShop1Dto();
//        FlowerShopEntity anotherAddedShop = flowerShopService.addFlowerShop(anotherFlowerShopDto);
//
//        FinishedProductDto anotherFinishedProductDto = createFinishedProduct1Dto();
//        anotherFinishedProductDto.setShopKey(anotherAddedShop.getShopKey());
//
//        assertNotNull(anotherAddedShop.getShopKey());
//        FinishedProductEntity anotherAddedFinishedProductEntity = finishedProductService.addFinishedProduct(anotherFinishedProductDto);
//
//        FPOrderCustomerDto anotherFpOrderCustomerDto = createFPOrderCustomerDto(anotherAddedFinishedProductEntity.getFpKey(), customerKey, "다른 국민카드 결제정보","20231204", "1900");
//        assertEquals(fpOrderService.customerOrderFinishedProduct(anotherFpOrderCustomerDto).getPickupDate(),"20231204");
//
//        ArrayList<FPOrderListDto> customerFpOrderListDtoArrayList = fpOrderService.customerFPOrderList(customerKey);
//        assertEquals(customerFpOrderListDtoArrayList.get(0).getPurchaseInfo(),"국민카드 결제정보");
//        assertEquals(customerFpOrderListDtoArrayList.get(1).getPurchaseInfo(),"다른 국민카드 결제정보");
//
//        ArrayList<FPOrderListDto> sellerFpOrderListDtoArrayList = fpOrderService.sellerFPOrderList(sellerKey0);
//        assertEquals(sellerFpOrderListDtoArrayList.get(0).getPurchaseInfo(),"국민카드 결제정보");
//
//    }
//
//    /*
//    고객, 판매자, 가게, 완제품이 미리 있어야함.
//     */
//
//
//}
