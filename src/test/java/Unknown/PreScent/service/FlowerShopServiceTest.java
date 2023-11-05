package Unknown.PreScent.service;

import Unknown.PreScent.dto.FlowerShop;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlowerShopServiceTest {
    @Autowired
    private FlowerShopService flowerShopService;

    @Test
    @DisplayName("매장 등록, 쿼리 테스트")
    public void testAddFlowerShop()
    {
        FlowerShop addedShop = flowerShopService.addFlowerShop(9, "its me", "031-308-8223", "suwon-si", new int[][] {{1, 2, 3},{3, 4, 5}},false, new String[]{"monday"});

        assertThat(addedShop).isNotNull();
        assertThat(addedShop.getSellerKey()).isEqualTo(9);

        Integer testShopKeyIndex = addedShop.getshopKey();

        FlowerShop retrievedShop = flowerShopService.getFlowerShopByshopKey(testShopKeyIndex).orElse(null);
        assertThat(retrievedShop).isNotNull();
        assertThat(retrievedShop.getShopName()).isEqualTo("its me");
    }
}
