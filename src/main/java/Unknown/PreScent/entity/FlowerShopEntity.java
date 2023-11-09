package Unknown.PreScent.entity;

import Unknown.PreScent.dto.FlowerShopDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "flower_shop")
public class FlowerShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopKey;
    @Column(nullable = false)
    private Integer sellerKey;
    @Column(nullable = false)
    private String shopName;
    @Column(nullable = false)
    private String shopPhoneNum;
    @Column(nullable = false)
    private String shopLocation;
    private int[][] openingHours;
    @Column(nullable = false)
    private boolean isOpened;
    private String[] holiday;
    @Column(nullable = false)
    private boolean isSub;
    @OneToOne(mappedBy = "flowerShop")
    private SellerEntity seller;

    public FlowerShopEntity(Integer shopKey, Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday, boolean isSub) {
        this.shopKey = shopKey;
        this.sellerKey = sellerKey;
        this.shopName = shopName;
        this.shopPhoneNum = shopPhoneNum;
        this.shopLocation = shopLocation;
        this.openingHours = openingHours;
        this.isOpened = isOpened;
        this.holiday = holiday;
        this.isSub = isSub;
    }
    public FlowerShopEntity(Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday) {
        this.sellerKey = sellerKey;
        this.shopName = shopName;
        this.shopPhoneNum = shopPhoneNum;
        this.shopLocation = shopLocation;
        this.openingHours = openingHours;
        this.isOpened = isOpened;
        this.holiday = holiday;
    }

    public FlowerShopEntity() {
    }

    public static FlowerShopEntity toFlowerShopEntity(FlowerShopDto flowerShopDto)
    {
        FlowerShopEntity flowerShopEntity = new FlowerShopEntity();
        flowerShopEntity.setShopKey(flowerShopDto.getShopKey());
        flowerShopEntity.setSellerKey(flowerShopDto.getSellerKey());
        flowerShopEntity.setShopName(flowerShopDto.getShopName());
        flowerShopEntity.setShopLocation(flowerShopDto.getShopLocation());
        flowerShopEntity.setOpeningHours(flowerShopDto.getOpeningHours());
        flowerShopEntity.setOpened(flowerShopDto.isOpened());
        flowerShopEntity.setHoliday(flowerShopDto.getHoliday());
        flowerShopEntity.setSub(flowerShopDto.isSub());
        return flowerShopEntity;
    }

}