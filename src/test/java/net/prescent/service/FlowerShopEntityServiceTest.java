package net.prescent.service;

import net.prescent.entity.FlowerShopEntity;
import net.prescent.repository.FlowerShopRepository;
import net.prescent.dto.SellerDto;
import net.prescent.repository.SellerRepository;
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
    @Test
    //@Transactional
    @DisplayName("매장 등록, 쿼리 테스트")
    public void testAddFlowerShop()
    {
        SellerDto sellerDto = createSellerDto();
        SellerDto sellerKey = sellerService.signup(sellerDto);
        assertNotNull(sellerKey);

        FlowerShopEntity addedShop = flowerShopService.addFlowerShop(123456789, "its me", "031-308-8223", "suwon-si", new int[][] {{1, 2, 3},{3, 4, 5}},false, new String[]{"monday"});

        assertThat(addedShop).isNotNull();
        assertThat(addedShop.getSellerEntity()).isNotNull();
        assertThat((addedShop.getSellerEntity()).getSellerKey()).isEqualTo(123456789);

        Integer testShopKeyIndex = addedShop.getShopKey();

        FlowerShopEntity retrievedShop = flowerShopService.getFlowerShopByshopKey(testShopKeyIndex).orElse(null);
        assertThat(retrievedShop).isNotNull();
        assertThat(retrievedShop.getShopName()).isEqualTo("its me");
    }

    @Test
    //@Transactional
    @DisplayName("Sellerkey같은 매장 생성 테스트")
    public void testSameSellerKeyShop()
    {
        SellerDto sellerDto = createSellerDto();
        SellerDto sellerKey = sellerService.signup(sellerDto);
        assertNotNull(sellerKey);

        FlowerShopEntity addedShop1 = flowerShopService.addFlowerShop(123456789, "its me", "031-308-8223", "suwon-si", new int[][] {{1, 2, 3},{3, 4, 5}},false, new String[]{"monday"});
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            flowerShopService.addFlowerShop(123456789, "its me", "031-308-8223", "suwon-si", new int[][] {{1, 2, 3},{3, 4, 5}},false, new String[]{"monday"});});
        assertEquals("이미 매장을 등록한 판매자입니다.", e.getMessage());
    }
}