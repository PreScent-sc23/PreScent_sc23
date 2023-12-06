package net.prescent.entity;

import net.prescent.dto.FlowerShopDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "flower_shop")
public class FlowerShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopKey;
    //@Column(nullable = false)
    private String shopName;
    //@Column(nullable = false)
    private String shopPhoneNum;
    //@Column(nullable = false)
    private String shopLocation;
    private boolean isOpened=true;

    private Integer openHour;
    private Integer openMinute;
    private Integer closeHour;
    private Integer closeMinute;
    private String[] workday;
    private String description;

    private boolean isSub;
    @OneToOne(mappedBy = "flowerShopEntity")
    private SellerEntity sellerEntity;
    @OneToMany(mappedBy = "flowerShopEntity", fetch = FetchType.EAGER)
    private Set<FinishedProductEntity> finishedProductEntityList = new HashSet<>();


    public void setFinishedProductEntityList(FinishedProductEntity finishedProductEntity)
    {
        if (this.finishedProductEntityList == null) {
            this.finishedProductEntityList = new HashSet<>();
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
//    public FlowerShopEntity(String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday) {
//        this.shopName = shopName;
//        this.shopPhoneNum = shopPhoneNum;
//        this.shopLocation = shopLocation;
//        this.isOpened = isOpened;
//        this.holiday = holiday;
//    }

    public FlowerShopEntity() {
    }

    public static FlowerShopEntity FlowerShopDtoToEntity(FlowerShopDto flowerShopDto)
    {
        FlowerShopEntity flowerShopEntity = new FlowerShopEntity();
        flowerShopEntity.setShopKey(flowerShopDto.getShopKey());
        flowerShopEntity.setShopName(flowerShopDto.getShopName());
        flowerShopEntity.setShopPhoneNum(flowerShopDto.getShopPhoneNum());
        flowerShopEntity.setShopLocation(flowerShopDto.getShopLocation());

        flowerShopEntity.setOpenHour(flowerShopDto.getOpenHour());
        flowerShopEntity.setOpenMinute(flowerShopDto.getOpenMinute());
        flowerShopEntity.setCloseHour(flowerShopDto.getCloseHour());
        flowerShopEntity.setCloseMinute(flowerShopDto.getCloseMinute());
        flowerShopEntity.setWorkday(flowerShopDto.getWorkday());

        flowerShopEntity.setDescription(flowerShopDto.getDescription());

        return flowerShopEntity;
    }

    // public void setSellerEntity(Optional<SellerEntity> sellerEntity) {
    // }
}