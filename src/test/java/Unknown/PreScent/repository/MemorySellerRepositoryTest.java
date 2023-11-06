package Unknown.PreScent.repository;

import Unknown.PreScent.dto.SellerDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemorySellerRepositoryTest {

    /*
    @Autowired
    SellerRepository sellerRepository;


    @Test
    public void save(){
        //SellerDto seller = new SellerDto();
        //seller.setSellerName("SangWon");
        SellerDto seller = new SellerDto();
        seller.setSellerKey(123456789L);
        seller.setSellerName("suhyeon");
        seller.setID("sooh");
        seller.setPassword("04prescent");
        seller.setSellerPhoneNum("010-1111-2222");

        List<SellerDto> sellers = Arrays.asList(seller);
        List<SellerDto> savedSellers = sellerRepository.saveAll(sellers);

        assertThat(savedSellers).isNotEmpty();
        assertThat(savedSellers.get(0).getSellerKey()).isEqualTo(seller.getSellerKey());

        //SellerDto result = sellerRepository.findBySellerKey(seller.getSellerKey()).get();
        //assertThat(seller).isEqualTo(result);
    }

    @Test
    public void findByName(){
        SellerDto seller1 = new SellerDto();
        seller1.setSellerName("SangWon1");
        sellerRepository.save(seller1);

        SellerDto seller2 = new SellerDto();
        seller2.setSellerName("SangWon2");
        sellerRepository.save(seller2);

        SellerDto result = sellerRepository.findBySellerName("SangWon1").get();

        assertThat(result).isEqualTo(seller1);
    }

    @Test
    public void findAll(){
        SellerDto seller1 = new SellerDto();
        seller1.setSellerName("SangWon1");
        sellerRepository.save(seller1);

        SellerDto seller2 = new SellerDto();
        seller2.setSellerName("SangWon2");
        sellerRepository.save(seller2);

        List<SellerDto> result = sellerRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

     */
}
