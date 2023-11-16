package Unknown.PreScent;

import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PreScentApplicationTests {

	/*
	@Autowired
	SellerRepository sellerRepository;

	@Test
	public void create(){
		SellerDto seller = new SellerDto();
		seller.setSellerKey(123456789L);
		seller.setSellerName("suhyeon");
		seller.setID("sooh");
		seller.setPassword("04prescent");
		seller.setSellerPhoneNum("010-1111-2222");

		SellerDto newUser = sellerRepository.save(seller);
		System.out.println(newUser);
	}

	@Test
	public void read(){
		Optional<SellerDto> seller = sellerRepository.findBySellerKey(L);

		seller.ifPresent(selectSeller ->{
			System.out.println("sellerKey: " + selectSeller.getSellerKey());
			System.out.println("sellerName: " + selectSeller.getSellerName());
			System.out.println("ID: " + selectSeller.getID());
			System.out.println("sellerPhoneNum: " + selectSeller.getSellerPhoneNum());
		});

	}
	 */
}
