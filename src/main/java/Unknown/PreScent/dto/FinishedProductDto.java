package Unknown.PreScent.dto;

import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.entity.FlowerShopEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
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
}
