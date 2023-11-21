package Unknown.PreScent.dto;

import Unknown.PreScent.entity.FinishedProductEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.Column;
import java.io.IOException;

@Getter
@Setter
@ToString
public class FinishedProductDto {
    private MultipartFile fpImage;
    private String fpName;
    private String fpTag;
    private Integer fpPrice;
    private String fpDetail;
    private String[] fpFlowerList;

    public FinishedProductDto(MultipartFile fpImage, String fpName, String fpTag, Integer fpPrice, String fpDetail, String[] fpFlowerList) {
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
