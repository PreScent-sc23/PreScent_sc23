package net.prescent.service;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.CustomerDto;
import net.prescent.dto.SellerDto;
import net.prescent.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@Transactional
@SpringBootTest
public class AccessTokenServiceTest {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private UserService userService;


    Integer tempNumForTest = 1;
    String tempStringForTest="0";


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

    @Test
    @DisplayName("사용자 토큰 테스트")
    public void AccessTokenCustomerTest() {
        CustomerDto customerDto = createTestCustomerDto();
        Integer customerKey = userService.signupCustomer(customerDto);

        String token = userService.login(customerDto.getIdEmail(), customerDto.getPassword());
        log.info("token정보 :" + token);

        CustomerEntity customerEntity = accessTokenService.getCustomerFromToken(token);
        assertNotNull(customerEntity);
        assertEquals(customerDto.getIdEmail(), customerEntity.getIdEmail());
    }

    @Test
    @DisplayName("판매자 토큰 테스트")
    public void AccessTokenSellerTest() {
        SellerDto sellerDto = createSellerDto();
        Integer sellerKey = userService.signupSeller(sellerDto);

        String token = userService.login(sellerDto.getIdEmail(), sellerDto.getPassword());
        log.info("token정보 :" + token);

        SellerEntity sellerEntity = accessTokenService.getSellerFromToken(token);
        assertNotNull(sellerEntity);
        assertEquals(sellerDto.getIdEmail(), sellerEntity.getIdEmail());
    }

}
