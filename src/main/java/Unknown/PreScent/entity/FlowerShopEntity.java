package Unknown.PreScent.entity;

import Unknown.PreScent.dto.FlowerShopDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
@Entity
@Table(name = "flower_shop")
public class FlowerShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopKey;
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
    @OneToOne(mappedBy = "flowerShopEntity")
    private SellerEntity sellerEntity;
    @OneToMany(mappedBy = "flowerShopEntity",fetch = FetchType.EAGER)
    private List<FinishedProductEntity> finishedProductEntityList;

    private String description;


    public void setFinishedProductEntityList(FinishedProductEntity finishedProductEntity)
    {
        if (this.finishedProductEntityList == null) {
            this.finishedProductEntityList = new ArrayList<>();
        }
        this.finishedProductEntityList.add(finishedProductEntity);
    }
    public void setSellerEntity(SellerEntity sellerEntity)
    {
        this.sellerEntity = sellerEntity;
        this.sellerEntity.setFlowerShopEntity(this);
    }
    public FlowerShopEntity(String shopName, String shopPhoneNum, String shopLocation, String description) {
        this.shopName = shopName;
        this.shopPhoneNum = shopPhoneNum;
        this.shopLocation = shopLocation;
        this.description = description;
    }
    public FlowerShopEntity(Integer shopKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday, boolean isSub) {
        this.shopKey = shopKey;
        this.shopName = shopName;
        this.shopPhoneNum = shopPhoneNum;
        this.shopLocation = shopLocation;
        this.openingHours = openingHours;
        this.isOpened = isOpened;
        this.holiday = holiday;
        this.isSub = isSub;
    }
    public FlowerShopEntity(String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday) {
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
        flowerShopEntity.setShopName(flowerShopDto.getShopName());
        flowerShopEntity.setShopLocation(flowerShopDto.getShopLocation());
        flowerShopEntity.setOpeningHours(flowerShopDto.getOpeningHours());
        flowerShopEntity.setOpened(flowerShopDto.isOpened());
        flowerShopEntity.setHoliday(flowerShopDto.getHoliday());
        flowerShopEntity.setSub(flowerShopDto.isSub());
        return flowerShopEntity;
    }

    // public void setSellerEntity(Optional<SellerEntity> sellerEntity) {
    // }
}