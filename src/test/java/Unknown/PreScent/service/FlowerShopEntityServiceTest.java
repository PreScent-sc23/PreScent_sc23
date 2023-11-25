package Unknown.PreScent.service;

import Unknown.PreScent.dto.FlowerShopDto;
import Unknown.PreScent.entity.FlowerShopEntity;
import Unknown.PreScent.repository.FlowerShopRepository;
import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
    private SellerService sellerService;

//    @BeforeEach
//    public void setup()
//    {
//        sellerRepository.deleteAllInBatch();
//        flowerShopRepository.deleteAllInBatch();
//    }
    public SellerDto createSellerDto(){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setSellerKey(12345678);
        sellerDto.setSellerName("suhyeonn");
        sellerDto.setSellerPhonenum("010-1111-2222");
        sellerDto.setSellerIdEmail("sooh");
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
    @Test
    @DisplayName("매장 등록, 쿼리 테스트")
    public void testAddFlowerShop()
    {
        SellerDto sellerDto = createSellerDto();
        SellerDto sellerKey = sellerService.signup(sellerDto);
        assertNotNull(sellerKey);

        FlowerShopDto flowerShopDto = createFlowerShopDto();
        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(flowerShopDto);

        assertThat(addedShop).isNotNull();
        assertThat(addedShop.getSellerEntity()).isNotNull();
        assertThat((addedShop.getSellerEntity()).getSellerKey()).isEqualTo(12345678);

        Integer testShopKeyIndex = addedShop.getShopKey();

        FlowerShopEntity retrievedShop = flowerShopService.getFlowerShopByshopKey(testShopKeyIndex).orElse(null);
        assertThat(retrievedShop).isNotNull();
        assertThat(retrievedShop.getShopName()).isEqualTo("it's me");
    }

    @Test
    @DisplayName("Sellerkey같은 매장 생성 테스트")
    public void testSameSellerKeyShop()
    {
        SellerDto sellerDto = createSellerDto();
        SellerDto sellerKey = sellerService.signup(sellerDto);
        assertNotNull(sellerKey);

        FlowerShopDto flowerShopDto = createFlowerShopDto();

        FlowerShopEntity addedShop1 = flowerShopService.addFlowerShop(flowerShopDto);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            flowerShopService.addFlowerShop(flowerShopDto);});
        assertEquals("이미 매장을 등록한 판매자입니다.", e.getMessage());
    }
}
