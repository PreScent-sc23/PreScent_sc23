package net.prescent.service;

import net.prescent.dto.CustomerDto;
import net.prescent.dto.FinishedProductDto;
import net.prescent.dto.FlowerShopDto;
import net.prescent.dto.SellerDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.repository.FinishedProductRepository;
import net.prescent.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class SearchServiceTest {
    @Autowired
    private FinishedProductService finishedProductService;

    @Autowired
    private FinishedProductRepository finishedProductRepository;

    @Autowired
    private SearchService searchService;
    @Autowired
    private UserService sellerService;
    @Autowired
    private FlowerShopService flowerShopService;
    @Autowired
    private SellerRepository sellerRepo;


    public FinishedProductDto createFinishedProductDto()
    {
        FinishedProductDto finishedProductDto = new FinishedProductDto();
        finishedProductDto.setFpName("장미꽃다발");
        finishedProductDto.setFpTag("#연인");
        finishedProductDto.setFpImage(null);
        finishedProductDto.setFpPrice(20000);
        finishedProductDto.setGetFpFlowerList("장미 안개꽃");
        return finishedProductDto;
    }

    private Integer tempNumForTest = 1;
    private String  tempStringForTest = "1";
    //    @BeforeEach
//    public void setup()
//    {
//        sellerRepository.deleteAllInBatch();
//        flowerShopRepository.deleteAllInBatch();
//    }
    public SellerDto createSellerDto(){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setBusinessKey(1234567890L+tempNumForTest);
        sellerDto.setName("kimkmim"+tempStringForTest);
        sellerDto.setPhonenum("010-1111-222"+tempStringForTest);
        sellerDto.setIdEmail("sooh"+tempStringForTest);
        sellerDto.setPassword("04prescent"+tempStringForTest);
        sellerDto.setConfirmPassword(("04prescent"+tempStringForTest));
        return sellerDto;
    }
    public FlowerShopDto createFlowerShopDto(){
        FlowerShopDto flowerShopDto = new FlowerShopDto();
        flowerShopDto.setShopName("it's me");
        flowerShopDto.setShopPhoneNum("031-308-8223");
        flowerShopDto.setAddress("suwon city");
        flowerShopDto.setLatitude(17.77);
        flowerShopDto.setLongitude(17.77);
        flowerShopDto.setFlowerListGetFromFE("장미 라넌큘려서");
        flowerShopDto.setOpenHour(10);
        flowerShopDto.setOpenMinute(0);
        flowerShopDto.setCloseHour(20);
        flowerShopDto.setCloseMinute(0);
        flowerShopDto.setWorkday(new String[]{"월", "화", "수", "목", "금"});
        flowerShopDto.setDescription("안녕하세요 디스크립션 입니다.");
        return flowerShopDto;
    }
    private CustomerDto createTestCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Test Customer");
        customerDto.setIdEmail("customer@test.com");
        customerDto.setPassword("password1");
        customerDto.setConfirmPassword("password1");
        customerDto.setPhonenum("010-1234-5678");
        customerDto.setAddress("Test Location");
        return customerDto;
    }
    public void seacrchTest()
    {
        SellerDto sellerDto = createSellerDto();
        Integer sellerKey = sellerService.signupSeller(sellerDto);

        CustomerDto customerDto = createTestCustomerDto();
        Integer userKey = sellerService.signupCustomer(customerDto);
        String token = sellerService.login("customer@test.com", "password1");

        FlowerShopDto flowerShopDto = createFlowerShopDto();
        flowerShopDto.setUserKey(sellerKey);
        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);


        FinishedProductDto finishedProductDto = createFinishedProductDto();
        finishedProductDto.setShopKey(addedShop.getShopKey());
        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(finishedProductDto);

        assertThat(addedFinishedProductEntity).isNotNull();

        Integer testFpIndex = addedFinishedProductEntity.getFpKey();

        FinishedProductEntity retrievedFP = finishedProductService.getFinishedProductWithFpKey(testFpIndex).orElse(null);
        assertThat(retrievedFP.getFpName()).isEqualTo("장미꽃다발");

        List<FinishedProductEntity> finishedProductEntityListByTag= searchService.searchByTagDefault("#연인").get();
        assertEquals(finishedProductEntityListByTag.get(0).getFpName(), "장미꽃다발");

        List<FinishedProductDto> finishedProductDtoListByFlower = searchService.searchByFlower(token, "안개");
        assertEquals(finishedProductDtoListByFlower.get(0).getFpName(),"장미꽃다발");


    }
}
