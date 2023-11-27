package net.prescent.dto;

import net.prescent.entity.FinishedProductEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Getter
@Setter
@ToString
public class FinishedProductDto {
    private Integer shopKey;
    private MultipartFile fpImage;
    private String fpName;
    private String fpTag;
    private Integer fpPrice;
    private String fpDetail;
    private String fpFlowerList;

    public FinishedProductDto(Integer shopKey, MultipartFile fpImage, String fpName, String fpTag, Integer fpPrice, String fpDetail, String fpFlowerList) {
        this.shopKey = shopKey;
        this.fpImage = fpImage;
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpPrice = fpPrice;
        this.fpDetail = fpDetail;
        this.fpFlowerList =fpFlowerList;
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
        finishedProductDto.setFpFlowerList(Arrays.toString(finishedProductEntity.getFpFlowerList()));
        return finishedProductDto;
    }

    public MultipartFile setFpImage(byte[] fpImage) {
        return null;
    }
}
