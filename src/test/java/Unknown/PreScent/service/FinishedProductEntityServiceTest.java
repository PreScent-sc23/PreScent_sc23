package Unknown.PreScent.service;

import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.repository.FinishedProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class FinishedProductEntityServiceTest {
    @Autowired
    private FinishedProductService finishedProductService;

    @Autowired
    private FinishedProductRepository finishedProductRepository;

    @Autowired
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        finishedProductRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("완제품 등록")
    public void testAddFinishedProduct()
    {
        FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(405, "장미꽃다발", "연인", null , 20000, true, new String[]{"장미","안개꽃"});

        //finishedProductRepository.findByShopKeyContaining(1);


        assertThat(addedFinishedProductEntity).isNotNull();

        Integer testFpIndex = addedFinishedProductEntity.getFpKey();

        FinishedProductEntity retrievedShop = finishedProductService.getFinishedProductWithFpKey(testFpIndex).orElse(null);
        assertThat(retrievedShop).isNotNull();
        assertThat(retrievedShop.getFpName()).isEqualTo("장미꽃다발");
    }

    @Test
    @DisplayName("완제품 복수 등록")
    public void testAddManyFinishedProduct()
    {
        //FinishedProductEntity addedFinishedProductEntity1 = finishedProductService.addFinishedProduct(1, "장미꽃다발1", "연인", null, 20000+1000, true, new String[]{"장미", "안개꽃"});
        for(int i = 1; i < 10; i++) {
            FinishedProductEntity addedFinishedProductEntity = finishedProductService.addFinishedProduct(i, "장미꽃다발"+i, "연인", null, 20000+(i*1000), true, new String[]{"장미", "안개꽃"});

            assertThat(addedFinishedProductEntity).isNotNull();

            Integer testFpIndex = addedFinishedProductEntity.getFpKey();

            FinishedProductEntity retrievedShop = finishedProductService.getFinishedProductWithFpKey(testFpIndex).orElse(null);
            assertThat(retrievedShop).isNotNull();
            assertThat(retrievedShop.getFpName()).isEqualTo("장미꽃다발"+i);
        }
    }

    @Test
    @DisplayName("태그 검색 수동")
    public void testTagSearch(){
        this.testAddManyFinishedProduct();
        String fpTag = "연인";
        Optional<List<FinishedProductEntity>> fpList = finishedProductService.getFinishedProductWithFpTag(fpTag);

        assertThat(fpList).isNotNull();

        List<FinishedProductEntity> finishedProductEntities = fpList.get();

        for(FinishedProductEntity fp : finishedProductEntities) System.out.println(fp.getFpPrice());
    }

    @Test
    @DisplayName("태그 검색 Page")
    public void testTagSearchPage(){
        this.testAddManyFinishedProduct();

        Page<Optional<FinishedProductEntity>> fpPage =  searchService.searchByTag("연인", "fpPrice", 0);

        assertThat(fpPage).isNotNull();

        System.out.println("---------Start---------Start---------");

        List<Optional<FinishedProductEntity>> fpList = fpPage.getContent();

        assertThat(fpList).isNotNull();

        for(Optional<FinishedProductEntity> fp : fpList) System.out.println(fp);


        System.out.println("---------End---------End---------");


    }

    @Test
    @DisplayName("태그 검색 List")
    public void testTagSearchSort(){
        this.testAddManyFinishedProduct();

        Optional<List<FinishedProductEntity>> searchResult =  searchService.searchByTagAsc("연인", "fpPrice");
        Optional<List<FinishedProductEntity>> searchResult1 =  searchService.searchByTagDesc("연인", "fpPrice");

        assertThat(searchResult).isNotNull();
        assertThat(searchResult1).isNotNull();

        System.out.println("---------Start---------Start---------");

        List<FinishedProductEntity> fpList = searchResult.get();
        List<FinishedProductEntity> fpList1 = searchResult1.get();

        assertThat(fpList).isNotNull();
        assertThat(fpList1).isNotNull();

        for(FinishedProductEntity fp : fpList) System.out.println(fp.getFpName() + "\t" + fp.getFpPrice());
        System.out.println("---------Asc---------Asc---------");
        for(FinishedProductEntity fp : fpList1) System.out.println(fp.getFpName() + "\t" + fp.getFpPrice());


        System.out.println("---------End---------End---------");


    }

}