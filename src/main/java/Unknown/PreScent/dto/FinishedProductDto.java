package Unknown.PreScent.dto;

import Unknown.PreScent.entity.FinishedProductEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.Column;
import java.io.IOException;
import java.util.List;

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

    public MultipartFile setFpImage(byte[] fpImage) {
        return null;
    }
}
