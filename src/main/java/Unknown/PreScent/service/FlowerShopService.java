package Unknown.PreScent.service;

import Unknown.PreScent.entity.FlowerShopEntity;
import Unknown.PreScent.entity.SellerEntity;
import Unknown.PreScent.repository.FlowerShopRepository;
import Unknown.PreScent.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlowerShopService {
    private final FlowerShopRepository flowerShopRepo;
    private final SellerRepository sellerRepo;

    public FlowerShopService(FlowerShopRepository flowerShopRepo, SellerRepository sellerRepo)
    {
        this.flowerShopRepo = flowerShopRepo;
        this.sellerRepo = sellerRepo;
    }


    public FlowerShopEntity addFlowerShop(Integer sellerKey, FlowerShopEntity flowerShopEntity)
    {
        Optional<SellerEntity> sellerEntity = sellerRepo.findBySellerKey(sellerKey);
        if(sellerEntity.isPresent()){
            SellerEntity foundSellerEntity = sellerEntity.get();
//            System.out.println(foundSellerEntity.getSellerId() + foundSellerEntity.getSellerName() + foundSellerEntity.getFlowerShopEntity().getShopName()+"-----------------------------------\n\n");

            System.out.println(foundSellerEntity.getSellerIdEmail() + foundSellerEntity.getSellerName() + "-----------------------------------\n\n");
            if(foundSellerEntity.getFlowerShopEntity() != null) {
                throw new IllegalStateException("이미 매장을 등록한 판매자입니다.");
            }
            else
            {
                flowerShopEntity.setSellerEntity(foundSellerEntity);
                flowerShopRepo.save(flowerShopEntity);
                sellerRepo.save(foundSellerEntity);
                System.out.println(foundSellerEntity.getSellerIdEmail() + foundSellerEntity.getSellerName() + foundSellerEntity.getFlowerShopEntity().getShopName()+"-----------------------------------\n\n");
            }
            return flowerShopEntity;
        }
        else{
            throw new IllegalStateException("판매자를 조회하지 못했습니다.");
        }
    }

    public FlowerShopEntity addFlowerShop(Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, String description)
    {
        FlowerShopEntity flowerShopEntity = new FlowerShopEntity(shopName, shopPhoneNum, shopLocation, description);
        Optional<SellerEntity> sellerEntity = sellerRepo.findBySellerKey(sellerKey);
        if(sellerEntity.isPresent()){
            SellerEntity foundSellerEntity = sellerEntity.get();
//            System.out.println(foundSellerEntity.getSellerId() + foundSellerEntity.getSellerName() + foundSellerEntity.getFlowerShopEntity().getShopName()+"-----------------------------------\n\n");

            System.out.println(foundSellerEntity.getSellerIdEmail() + foundSellerEntity.getSellerName() + "-----------------------------------\n\n");
            if(foundSellerEntity.getFlowerShopEntity() != null) {
                throw new IllegalStateException("이미 매장을 등록한 판매자입니다.");
            }
            else
            {
                flowerShopEntity.setSellerEntity(foundSellerEntity);
                flowerShopRepo.save(flowerShopEntity);
                sellerRepo.save(foundSellerEntity);
                System.out.println(foundSellerEntity.getSellerIdEmail() + foundSellerEntity.getSellerName() + foundSellerEntity.getFlowerShopEntity().getShopName()+"-----------------------------------\n\n");
            }
            return flowerShopEntity;
        }
        else{
            // 일치하는 seller없음. 시스템 오류 seller가 서버에 저장되어 있지 않음
            System.out.print("-----------------------------------------sellerEntity를 sellerKey로 찾았는데 없단다 -----------------------------------------");
        }
        return flowerShopRepo.save(flowerShopEntity);
    }

    public void addFlowerShopSchedule(){

    }

    public Optional<FlowerShopEntity> getFlowerShopByshopKey(Integer shopKey)
    {
        return flowerShopRepo.findByshopKey(shopKey);
    }

}
