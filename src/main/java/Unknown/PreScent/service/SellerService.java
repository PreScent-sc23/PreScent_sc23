package Unknown.PreScent.service;

import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.repository.DBSellerRepository;
import Unknown.PreScent.repository.SellerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class SellerService {

    private final SellerRepository sellerRepository = new DBSellerRepository();

    private PasswordEncoder passwordEncoder;

    @Transactional
    public Long signup(SellerDto seller){

        validateDuplicatedSeller(seller);

        String encoded;
        encoded = passwordEncoder.encode(seller.getPassword());
        seller.setPassword(encoded);

        sellerRepository.save(seller);

        return seller.getSellerKey();
    }

    private void validateDuplicatedSeller(SellerDto seller) {
        sellerRepository.findBySellerKey(seller.getSellerKey())
                .ifPresent(s ->{
                    throw new IllegalStateException("이미 등록된 사업자입니다.");
                });
        sellerRepository.findByID(seller.getID())
                .ifPresent(s ->{
                    throw new IllegalStateException("이미 사용중인 아이디입니다.");
                });
    }


    public List<SellerDto> findSellers(){
        return sellerRepository.findAll();
    }

    public Optional<SellerDto> findOne(Long sellerId){
        return sellerRepository.findBySellerKey(sellerId);
    }

}
