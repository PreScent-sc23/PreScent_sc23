package Unknown.PreScent.dto;

import Unknown.PreScent.entity.FinishedProductEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class FinishedProductDto {
    private Integer fpKey;
    private Integer shopKey;
    private String fpName;
    private String fpTag;
    private String fpImage;
    private Integer fpPrice;
    private boolean fpState=false;
    private String[] fpFlowerList;

    public static FinishedProductDto toFinishedProductDto(FinishedProductEntity finishedProductEntity)
    {
        FinishedProductDto finishedProductDto = new FinishedProductDto();

        finishedProductDto.setFpKey(finishedProductEntity.getFpKey());
        finishedProductDto.setFpName(finishedProductEntity.getFpName());
        finishedProductDto.setFpTag(finishedProductEntity.getFpTag());
        finishedProductDto.setFpImage(finishedProductEntity.getFpImage());
        finishedProductDto.setFpPrice(finishedProductEntity.getFpPrice());
        finishedProductDto.setFpState(finishedProductEntity.isFpState());
        finishedProductDto.setFpFlowerList(finishedProductEntity.getFpFlowerList());
        return finishedProductDto;
    }
}
