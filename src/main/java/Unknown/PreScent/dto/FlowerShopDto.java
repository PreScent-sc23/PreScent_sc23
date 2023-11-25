package Unknown.PreScent.dto;

import Unknown.PreScent.entity.FlowerShopEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlowerShopDto {
    private Integer sellerKey;
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

        flowerShopDto.setSellerKey(flowerShopEntity.getSellerEntity().getSellerKey());
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