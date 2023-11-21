package Unknown.PreScent.service;

import Unknown.PreScent.entity.FlowerShopEntity;
import Unknown.PreScent.repository.FlowerShopRepository;
import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static Unknown.PreScent.entity.SellerEntity.toSellerEntity;
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

    @BeforeEach
    public void setup()
    {
        sellerRepository.deleteAllInBatch();
        flowerShopRepository.deleteAllInBatch();
    }
    public SellerDto createSellerDto(){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setSellerKey(123456789);
        sellerDto.setSellerName("suhyeon");
        sellerDto.setSellerPhonenum("010-1111-2222");
        sellerDto.setSellerIdEmail("sooh");
        sellerDto.setSellerPassword("04prescent");
        return sellerDto;
    }
    public FlowerShopEntity createFlowerShopEntity(){
        FlowerShopEntity flowerShopEntity = new FlowerShopEntity();
        flowerShopEntity.setShopName("it's me");
        flowerShopEntity.setShopPhoneNum("031-308-8223");
        flowerShopEntity.setShopLocation("suwon city");
        flowerShopEntity.setOpeningHours(new int[][] {{1, 2, 3},{3, 4, 5}});
        flowerShopEntity.setHoliday(new String[]{"monday"});
        return flowerShopEntity;
    }
    @Test
    @DisplayName("매장 등록, 쿼리 테스트")
    public void testAddFlowerShop()
    {
        SellerDto sellerDto = createSellerDto();
        SellerDto sellerKey = sellerService.signup(sellerDto);
        assertNotNull(sellerKey);

        FlowerShopEntity flowerShopEntity = createFlowerShopEntity();
        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(123456789, flowerShopEntity);

        assertThat(addedShop).isNotNull();
        assertThat(addedShop.getSellerEntity()).isNotNull();
        assertThat((addedShop.getSellerEntity()).getSellerKey()).isEqualTo(123456789);

        Integer testShopKeyIndex = addedShop.getShopKey();

        FlowerShopEntity retrievedShop = flowerShopService.getFlowerShopByshopKey(testShopKeyIndex).orElse(null);
        assertThat(retrievedShop).isNotNull();
        assertThat(retrievedShop.getShopName()).isEqualTo("it's me");
    }

    @Test
    //@Transactional
    @DisplayName("Sellerkey같은 매장 생성 테스트")
    public void testSameSellerKeyShop()
    {
        SellerDto sellerDto = createSellerDto();
        SellerDto sellerKey = sellerService.signup(sellerDto);
        assertNotNull(sellerKey);

        FlowerShopEntity flowerShopEntity = createFlowerShopEntity();

        FlowerShopEntity addedShop1 = flowerShopService.addFlowerShop(123456789, flowerShopEntity);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            flowerShopService.addFlowerShop(123456789, flowerShopEntity);});
        assertEquals("이미 매장을 등록한 판매자입니다.", e.getMessage());
    }
}
