package Unknown.PreScent.service;

import Unknown.PreScent.entity.FinishedProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class FinishedProductEntityServiceTest {
    @Autowired
    private FinishedProductService finishedProductService;

    @Test
    @DisplayName("완제품 등록")
    public void testAddFinishedProduct()
    {
        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(1, "장미꽃다발", "연인", null , 20000, true, new String[]{"장미","안개꽃"});

        assertThat(addedFinishedProductEntity).isNotNull();

        Integer testFpIndex = addedFinishedProductEntity.getFpKey();

        FinishedProductEntity retrievedShop = finishedProductService.getFinishedProductWithFpKey(testFpIndex).orElse(null);
        assertThat(retrievedShop).isNotNull();
        assertThat(retrievedShop.getFpName()).isEqualTo("장미꽃다발");
    }
}