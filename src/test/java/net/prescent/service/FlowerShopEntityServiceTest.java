package net.prescent.service;

import net.prescent.dto.FlowerShopDto;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.repository.FlowerShopRepository;
import net.prescent.dto.SellerDto;
import net.prescent.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
public class FlowerShopEntityServiceTest {
    @Autowired
    private FlowerShopService flowerShopService;

    @Autowired
    private FlowerShopRepository flowerShopRepository;

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserService sellerService;

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
    @Test
    @DisplayName("매장 등록, 쿼리 테스트")
    public void testAddFlowerShop()
    {
        SellerDto sellerDto = createSellerDto();
        Integer sellerKey = sellerService.signupSeller(sellerDto);
        assertNotNull(sellerKey);

        FlowerShopDto flowerShopDto = createFlowerShopDto();
        flowerShopDto.setUserKey(sellerKey);
        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);

        assertThat(addedShop).isNotNull();
        assertThat(addedShop.getSellerEntity()).isNotNull();
        assertThat((addedShop.getSellerEntity()).getBusinessKey()).isEqualTo(1234567890L+tempNumForTest);

        Integer testShopKeyIndex = addedShop.getShopKey();

        FlowerShopEntity retrievedShop = flowerShopService.getFlowerShopByshopKey(testShopKeyIndex).orElse(null);
        assertThat(retrievedShop).isNotNull();
        assertThat(retrievedShop.getShopName()).isEqualTo("it's me");
    }

    @Test
    @DisplayName("Businesskey같은 매장 생성 테스트")
    public void testSameSellerKeyShop()
    {
        SellerDto sellerDto = createSellerDto();
        Integer sellerKey = sellerService.signupSeller(sellerDto);
        assertNotNull(sellerKey);

        FlowerShopDto flowerShopDto = createFlowerShopDto();
        flowerShopDto.setUserKey(sellerKey);

        FlowerShopEntity addedShop1 = flowerShopService.addFlowerShop(flowerShopDto);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            flowerShopService.addFlowerShop(flowerShopDto);});
        assertEquals("이미 매장을 등록한 판매자입니다.", e.getMessage());
    }
}
