package Unknown.PreScent.repository;

import Unknown.PreScent.dto.SellerDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemorySellerRepositoryTest {

    DBSellerRepository repository = new DBSellerRepository();


    @Test
    public void save(){
        SellerDto seller = new SellerDto();
        seller.setSellerName("SangWon");

        repository.save(seller);

        SellerDto result = repository.findBySellerKey(seller.getSellerKey()).get();
        assertThat(seller).isEqualTo(result);
    }

    @Test
    public void findByName(){
        SellerDto seller1 = new SellerDto();
        seller1.setSellerName("SangWon1");
        repository.save(seller1);

        SellerDto seller2 = new SellerDto();
        seller2.setSellerName("SangWon2");
        repository.save(seller2);

        SellerDto result = repository.findBySellerName("SangWon1").get();

        assertThat(result).isEqualTo(seller1);
    }

    @Test
    public void findAll(){
        SellerDto seller1 = new SellerDto();
        seller1.setSellerName("SangWon1");
        repository.save(seller1);

        SellerDto seller2 = new SellerDto();
        seller2.setSellerName("SangWon2");
        repository.save(seller2);

        List<SellerDto> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
