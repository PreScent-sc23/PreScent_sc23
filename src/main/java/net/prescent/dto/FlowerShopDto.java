package net.prescent.dto;

import net.prescent.entity.FlowerShopEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlowerShopDto {
    private Integer userKey;
    private Integer shopKey;
    private String shopName;
    private String shopPhoneNum;
    private String shopLocation;
    private String[] storedFlowerList;
    private String flowerListGetFromFE;

    private Integer openHour;
    private Integer openMinute;
    private Integer closeHour;
    private Integer closeMinute;
    private String[] workday;
    private String description;

    public FlowerShopDto(String shopName, String shopPhoneNum, String shopLocation, String flowerListGetFromFE, Integer openHour, Integer openMinute, Integer closeHour, Integer closeMinute, String[] workday, String description)
    {
        this.shopName = shopName;
        this.shopPhoneNum = shopPhoneNum;
        this.shopLocation = shopLocation;
        this.flowerListGetFromFE=flowerListGetFromFE;
        this.openHour = openHour;
        this.openMinute = openMinute;
        this.closeHour = closeHour;
        this.closeMinute = closeMinute;
        this.workday = workday;
        this.description = description;
    }


    public static FlowerShopDto FlowerShopEntityToDto(FlowerShopEntity flowerShopEntity) {
        FlowerShopDto flowerShopDto = new FlowerShopDto();

        flowerShopDto.setUserKey(flowerShopEntity.getSellerEntity().getUserKey());
        flowerShopDto.setShopKey(flowerShopEntity.getShopKey());
        flowerShopDto.setShopName(flowerShopEntity.getShopName());
        flowerShopDto.setShopPhoneNum(flowerShopEntity.getShopPhoneNum());
        flowerShopDto.setShopLocation(flowerShopEntity.getShopLocation());
        flowerShopDto.setOpenHour(flowerShopEntity.getOpenHour());
        flowerShopDto.setOpenMinute(flowerShopEntity.getOpenMinute());
        flowerShopDto.setStoredFlowerList(flowerShopEntity.getFlowerList());
        flowerShopDto.setCloseHour(flowerShopEntity.getCloseHour());
        flowerShopDto.setCloseMinute(flowerShopEntity.getCloseMinute());
        flowerShopDto.setWorkday(flowerShopEntity.getWorkday());
        flowerShopDto.setDescription(flowerShopEntity.getDescription());

        return flowerShopDto;
    }
}