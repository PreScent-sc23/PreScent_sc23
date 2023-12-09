package net.prescent.dto;

import net.prescent.entity.FinishedProductEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishedProductDto {
    private Integer fpKey;
    private Integer shopKey;
    private String fpImage;
    private String fpName;
    private String fpTag;
    private Integer fpPrice;
    private String fpDetail;
    private String[] fpFlowerList;
    private String getFpFlowerList;


    public FinishedProductDto(Integer fpKey, Integer shopKey, String fpImage, String fpName, String fpTag, Integer fpPrice, String fpDetail, String[] fpFlowerList) {
        this.fpKey = fpKey;
        this.shopKey = shopKey;
        this.fpImage = fpImage;
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpPrice = fpPrice;
        this.fpDetail = fpDetail;
        this.fpFlowerList = fpFlowerList;
    }

    public FinishedProductDto() {

    }
    public static FinishedProductDto toFinishedProductDto(FinishedProductEntity finishedProductEntity)
    {
        FinishedProductDto finishedProductDto = new FinishedProductDto();

        finishedProductDto.setFpKey(finishedProductEntity.getFpKey());
        finishedProductDto.setFpImage(finishedProductEntity.getFpImage());
        finishedProductDto.setFpName(finishedProductEntity.getFpName());
        finishedProductDto.setFpTag(finishedProductEntity.getFpTag());
        finishedProductDto.setFpPrice(finishedProductEntity.getFpPrice());
        finishedProductDto.setFpDetail(finishedProductEntity.getFpDetail());
        finishedProductDto.setFpFlowerList(finishedProductEntity.getFpFlowerList());
        return finishedProductDto;
    }

//    public static FinishedProductDto toFinishedProductDto2(FinishedProductEntity finishedProductEntity)
//    {
//        FinishedProductDto finishedProductDto = new FinishedProductDto();
//
//        finishedProductDto.setFpKey(finishedProductEntity.getFpKey());
//        finishedProductDto.setFpImage(finishedProductEntity.getFpImage());
//        finishedProductDto.setFpName(finishedProductEntity.getFpName());
//        finishedProductDto.setFpTag(finishedProductEntity.getFpTag());
//        finishedProductDto.setFpPrice(finishedProductEntity.getFpPrice());
//        finishedProductDto.set
////        finishedProductDto.setShopKey(finishedProductEntity.getShopKey());
//        return finishedProductDto;
//    }

    public void setFpFlowerList(String[] fpFlowerList) {
        this.fpFlowerList = fpFlowerList;
    }
}
