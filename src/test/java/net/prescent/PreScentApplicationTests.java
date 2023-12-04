package net.prescent;

import org.springframework.boot.test.context.SpringBootTest;

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
		log.debug(newUser);
	}

	@Test
	public void read(){
		Optional<SellerDto> seller = sellerRepository.findBySellerKey(L);

		seller.ifPresent(selectSeller ->{
			log.debug("sellerKey: " + selectSeller.getSellerKey());
			log.debug("sellerName: " + selectSeller.getSellerName());
			log.debug("ID: " + selectSeller.getID());
			log.debug("sellerPhoneNum: " + selectSeller.getSellerPhoneNum());
		});

	}
	 */
}
