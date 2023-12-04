//package net.prescent.service;
//
//
//import net.prescent.dto.CustomerDto;
//import net.prescent.dto.FlowerShopDto;
//import net.prescent.dto.SellerDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
////@Transactional
//public class insertingExampleTest {
//
//    final private UserService userService;
//    final private FlowerShopService flowerShopService;
//
//    public insertingExampleTest(UserService userService, FlowerShopService flowerShopService) {
//        this.userService = userService;
//        this.flowerShopService = flowerShopService;
//    }
//
//    public FlowerShopDto createFlowerShopDto(){
//        FlowerShopDto flowerShopDto = new FlowerShopDto();
//        flowerShopDto.setBusinessKey(1111111111L);
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
//    public SellerDto createSellerDto1(Long businessKey) {
//        SellerDto sellerDto = new SellerDto();
//        sellerDto.setBusinessKey(businessKey);
//        sellerDto.setName("예시 사업자");
//        sellerDto.setPhonenum("010-1111-2222");
//        sellerDto.setIdEmail("seller@naver.com");
//        sellerDto.setPassword("1234");
//        sellerDto.setConfirmPassword(("1234"));
//        return sellerDto;
//    }
//    private CustomerDto createTestCustomerDto() {
//        CustomerDto customerDto = new CustomerDto();
//        customerDto.setName("예시 구매자");
//        customerDto.setIdEmail("buyer@naver.com");
//        customerDto.setPassword("12345");
//        customerDto.setConfirmPassword("12345");
//        customerDto.setPhonenum("010-2222-3333");
//        customerDto.setLocation("Test Location");
//        return customerDto;
//    }
//
//    @Test
//    public void insertingExampleMethod()
//    {
//        SellerDto sellerDto = createSellerDto1(1111111111L);
//        Throwable e = assertThrows(IllegalStateException.class, () -> {
//            userService.signupSeller(sellerDto);});
//
//        FlowerShopDto flowerShopDto = createFlowerShopDto();
//        Throwable err = assertThrows(IllegalStateException.class, () -> {
//            flowerShopService.addFlowerShop(flowerShopDto);});
//
//
//        CustomerDto customerDto = createTestCustomerDto();
//        Throwable error = assertThrows(IllegalStateException.class, () -> {
//            userService.signupCustomer(customerDto);});
//    }
//
//}
