package Unknown.PreScent.dto;

import Unknown.PreScent.entity.FlowerShopEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlowerShopDto {
    private Integer shopKey;
    private Integer sellerKey;
    private String shopName;
    private String shopPhoneNum;
    private String shopLocation;
    private int[][] openingHours;
    private boolean isOpened = false;
    private String[] holiday;
    private boolean isSub = false;


    public static FlowerShopDto toFlowerShopDto(FlowerShopEntity flowerShopEntity) {
        FlowerShopDto flowerShopDto = new FlowerShopDto();

        flowerShopDto.setShopKey(flowerShopEntity.getShopKey());
        flowerShopDto.setSellerKey(flowerShopEntity.getSellerKey());
        flowerShopDto.setShopName(flowerShopEntity.getShopName());
        flowerShopDto.setShopPhoneNum(flowerShopEntity.getShopPhoneNum());
        flowerShopDto.setShopLocation(flowerShopEntity.getShopLocation());
        flowerShopDto.setOpeningHours(flowerShopEntity.getOpeningHours());
        flowerShopDto.setOpened(flowerShopEntity.isOpened());
        flowerShopDto.setHoliday(flowerShopEntity.getHoliday());
        flowerShopDto.setSub(flowerShopEntity.isSub());
        return flowerShopDto;
    }
}