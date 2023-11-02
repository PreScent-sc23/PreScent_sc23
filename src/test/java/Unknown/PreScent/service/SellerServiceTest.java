package Unknown.PreScent.service;

import Unknown.PreScent.dto.SellerDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

//PreScent_sc23\src\test\java\Unknown\PreScent\service\SellerServiceTest.java
public class SellerServiceTest {

    SellerService sellerService = new SellerService();

    @Test
     void join() {
        //given
        SellerDto seller = new SellerDto();
        seller.setSellerName("hello");

        //when
        Long saveId = sellerService.signup(seller);

        //then
        SellerDto findSeller = sellerService.findOne(saveId).get();
        assertThat(seller.getSellerName()).isEqualTo(findSeller.getSellerName());
    }

    @Test
    void findSellers() {
    }

    @Test
    void findOne() {
    }
}