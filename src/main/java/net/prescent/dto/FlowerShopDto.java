package net.prescent.dto;

import net.prescent.entity.FlowerShopEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlowerShopDto {
    private Long BusinessKey;
    private Integer shopKey;
    private String shopName;
    private String shopPhoneNum;
    private String shopLocation;

    private Integer openHour;
    private Integer openMinute;
    private Integer closeHour;
    private Integer closeMinute;
    private String[] workday;
    private String description;


    public static FlowerShopDto FlowerShopEntityToDto(FlowerShopEntity flowerShopEntity) {
        FlowerShopDto flowerShopDto = new FlowerShopDto();

        flowerShopDto.setBusinessKey(flowerShopEntity.getSellerEntity().getBusinessKey());
        flowerShopDto.setShopKey(flowerShopEntity.getShopKey());
        flowerShopDto.setShopName(flowerShopEntity.getShopName());
        flowerShopDto.setShopPhoneNum(flowerShopEntity.getShopPhoneNum());
        flowerShopDto.setShopLocation(flowerShopEntity.getShopLocation());
        flowerShopDto.setOpenHour(flowerShopEntity.getOpenHour());
        flowerShopDto.setOpenMinute(flowerShopEntity.getOpenMinute());
        flowerShopDto.setCloseHour(flowerShopEntity.getCloseHour());
        flowerShopDto.setCloseMinute(flowerShopEntity.getCloseMinute());
        flowerShopDto.setWorkday(flowerShopEntity.getWorkday());
        flowerShopDto.setDescription(flowerShopEntity.getDescription());

        return flowerShopDto;
    }
}