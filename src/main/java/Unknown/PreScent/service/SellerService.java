package Unknown.PreScent.service;

import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.entity.SellerEntity;
import Unknown.PreScent.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SellerDto signup(SellerDto sellerDto) {
        validateDuplicatedSeller(sellerDto.getSellerKey());

        SellerEntity sellerEntity = SellerEntity.toSellerEntity(sellerDto);
        sellerEntity.setSellerPassword(passwordEncoder.encode(sellerDto.getSellerPassword()));

        SellerEntity savedEntity = sellerRepository.save(sellerEntity);
        return SellerDto.toSellerDto(savedEntity);
    }

    private void validateDuplicatedSeller(Integer sellerKey) {
        sellerRepository.findBySellerKey(sellerKey)
                .ifPresent(s -> {
                    throw new IllegalStateException("이미 등록된 사업자입니다.");
                });
    }

    public SellerDto login(String id, String password, HttpSession session) {

        SellerEntity seller = sellerRepository.findBySellerIdEmail(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 판매자 Email입니다."));

        if (passwordEncoder.matches(password, seller.getSellerPassword())) {
            session.setAttribute("sellerKey", seller.getSellerKey());
            return SellerDto.toSellerDto(seller);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}