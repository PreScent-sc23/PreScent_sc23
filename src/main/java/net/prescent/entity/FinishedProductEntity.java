package net.prescent.entity;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FinishedProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Slf4j
@Table(name = "finished_product")
public class FinishedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fpKey;

    @Column(nullable = false)
    private String fpName;
    private String fpTag;
    @Column(name = "fpImage")
    private String fpImage;
    @Column(nullable = false)
    private Integer fpPrice;
    @Column(nullable = false)
    private boolean fpState = true;

    private String[] fpFlowerList;
//    private String fpFlowerList;

    private String fpDetail;

    @ManyToOne
    @JoinColumn(name = "shopKey")
    private FlowerShopEntity flowerShopEntity;

    public void setFlowerShopEntity(FlowerShopEntity flowerShopEntity)
    {
        this.flowerShopEntity = flowerShopEntity;
        this.flowerShopEntity.setFinishedProductEntityList(this);
    }

    public FinishedProductEntity(String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList) { // 테스트용
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpImage = fpImage;
        this.fpPrice = fpPrice;
        this.fpState = fpState;
        this.fpFlowerList = fpFlowerList;
    }

    public FinishedProductEntity() {
    }

    public static FinishedProductEntity finishedProductDtotoEntity(FinishedProductDto finishedProductDto)
    {
        FinishedProductEntity finishedProductEntity = new FinishedProductEntity();
            if(finishedProductDto.getFpImage()!=null)
            {
                finishedProductEntity.setFpImage(finishedProductDto.getFpImage());
                log.debug("여긴 DtoToEntity내부 fpImage여부를 확인"+finishedProductEntity.getFpImage()+"---------------");
            }
            else {
                log.debug("DtoToEntity내부 fpImage가 비어있습니다 ******************************");
            }
        finishedProductEntity.setFpName(finishedProductDto.getFpName());
        finishedProductEntity.setFpTag(finishedProductDto.getFpTag());
        finishedProductEntity.setFpPrice(finishedProductDto.getFpPrice());
        finishedProductEntity.setFpDetail(finishedProductDto.getFpDetail());
        finishedProductEntity.setFpFlowerList(finishedProductDto.getGetFpFlowerList().split(",| "));
        return finishedProductEntity;
    }
}
