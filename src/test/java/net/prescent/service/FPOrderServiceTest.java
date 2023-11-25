package net.prescent.service;

import net.prescent.dto.*;
import net.prescent.entity.FPOrderEntity;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.entity.CustomerEntity;

import net.prescent.repository.FPOrderRepository;
import net.prescent.repository.SellerRepository;
import net.prescent.repository.CustomerRepository;
import net.prescent.repository.FlowerShopRepository;
import net.prescent.repository.FinishedProductRepository;

import net.prescent.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class FPOrderServiceTest {
    @Autowired
    FPOrderRepository fpOrderRepo;
    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;
    @Autowired
    FlowerShopService flowerShopService;
    @Autowired
    FinishedProductService finishedProductService;
    @Autowired
    FPOrderService fpOrderService;
    @Autowired
    PasswordEncoder passwordEncoder;
    public SellerDto createSellerDto(Integer sellerKey) {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setSellerKey(sellerKey);
        sellerDto.setSellerName("suhyeon");
        sellerDto.setSellerPhonenum("010-1111-2222");
        sellerDto.setSellerIdEmail("ajou@gmail.com");
        sellerDto.setSellerPassword("04prescent");
        return sellerDto;
    }

    public FlowerShopDto createFlowerShopDto(){
        FlowerShopDto flowerShopDto = new FlowerShopDto();
        flowerShopDto.setSellerKey(12345678);
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
        finishedProductDto.setFpImage(null);
        finishedProductDto.setFpPrice(20000);
        finishedProductDto.setFpFlowerList(new String[]{"장미", "안개꽃"});
        return finishedProductDto;
    }

    public CustomerDto createCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerName("suhyeon");
        customerDto.setCustomerPhonenum("010-1111-2222");
        customerDto.setCustomerIdEmail("ajou.gmail.com");
        customerDto.setCustomerPassword("04prescent");
        return customerDto;
    }

    public FPOrderCustomerDto createFPOrderCustomerDto(Integer fpKey, Integer customerKey,String purchaseInfo, Date pickupDate)
    {
        FPOrderCustomerDto fpOrderCustomerDto = new FPOrderCustomerDto();
        fpOrderCustomerDto.setFpKey(fpKey);
        fpOrderCustomerDto.setCustomerKey(customerKey);
        fpOrderCustomerDto.setPurchaseInfo(purchaseInfo);
        fpOrderCustomerDto.setPickupDate(pickupDate);
        return fpOrderCustomerDto;
    }
    //테스트 목록. 주문서 작성.
    @Test
    @DisplayName("고객 완제품 주문 테스트")
    public void customerFPOrderTest()
    {
        CustomerDto customerDto = createCustomerDto();
        Integer customerKey = customerService.signupForTest(customerDto);
        assertNotNull(customerKey);

        SellerDto sellerDto = createSellerDto(12345678);
        SellerDto savedSellerDto = sellerService.signup(sellerDto);

        FlowerShopDto flowerShopDto = createFlowerShopDto();
        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);

        FinishedProductDto finishedProductDto = createFinishedProductDto();
        assertNotNull(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(addedShop.getShopKey(), finishedProductDto);

        Date inputDate = new Date();
        FPOrderCustomerDto fpOrderCustomerDto = createFPOrderCustomerDto(addedFinishedProductEntity.getFpKey(), customerKey, "국민카드 결제정보",inputDate);
        assertEquals(fpOrderService.customerOrderFinishedProduct(fpOrderCustomerDto).getPickupDate(),inputDate);
    }

    /*
    고객, 판매자, 가게, 완제품이 미리 있어야함.
     */


}
