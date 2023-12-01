package net.prescent.dto;

import net.prescent.entity.FinishedProductEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class FinishedProductDto {
    private Integer fpKey;
    private MultipartFile fpImage;
    private String fpName;
    private String fpTag;
    private Integer fpPrice;
    private String fpDetail;
    private String[] fpFlowerList;
    private Integer shopKey;

    public FinishedProductDto(Integer fpKey, MultipartFile fpImage, String fpName, String fpTag, Integer fpPrice, String fpDetail, String[] fpFlowerList, Integer shopKey) {
        this.fpKey = fpKey;
        this.fpImage = fpImage;
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpPrice = fpPrice;
        this.fpDetail = fpDetail;
        this.fpFlowerList =fpFlowerList;
        this.shopKey = shopKey;
    }

    public FinishedProductDto() {

    }

    public static FinishedProductDto toFinishedProductDto(FinishedProductEntity finishedProductEntity)
    {
        FinishedProductDto finishedProductDto = new FinishedProductDto();

        finishedProductDto.setFpImage(finishedProductEntity.getFpImage());
        finishedProductDto.setFpName(finishedProductEntity.getFpName());
        finishedProductDto.setFpTag(finishedProductEntity.getFpTag());
        finishedProductDto.setFpPrice(finishedProductEntity.getFpPrice());
        finishedProductDto.setFpDetail(finishedProductEntity.getFpDetail());
        finishedProductDto.setFpFlowerList(finishedProductEntity.getFpFlowerList());
        return finishedProductDto;
    }

    public static FinishedProductDto toFinishedProductDto2(FinishedProductEntity finishedProductEntity)
    {
        FinishedProductDto finishedProductDto = new FinishedProductDto();

        finishedProductDto.setFpKey(finishedProductEntity.getFpKey());
        finishedProductDto.setFpImage(finishedProductEntity.getFpImage());
        finishedProductDto.setFpName(finishedProductEntity.getFpName());
        finishedProductDto.setFpTag(finishedProductEntity.getFpTag());
        finishedProductDto.setFpPrice(finishedProductEntity.getFpPrice());
//        finishedProductDto.setShopKey(finishedProductEntity.getShopKey());
        return finishedProductDto;
    }

    public MultipartFile setFpImage(byte[] fpImage) {
        return null;
    }
}
