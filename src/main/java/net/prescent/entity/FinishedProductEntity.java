package net.prescent.entity;

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


//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "shopKey")
    private FlowerShopEntity flowerShopEntity;

    @OneToMany(mappedBy = "finishedProductEntity",fetch = FetchType.EAGER)
    private List<FPOrderEntity> fpOrderEntityList= new ArrayList<>();

    public void setFlowerShopEntity(FlowerShopEntity flowerShopEntity)
    {
        this.flowerShopEntity = flowerShopEntity;
        this.flowerShopEntity.setFinishedProductEntityList(this);
    }

    public void setFpOrderEntityList(FPOrderEntity fpOrderEntity)
    {
        if(this.fpOrderEntityList == null)
        {
            fpOrderEntityList = new ArrayList<>();
        }
        this.fpOrderEntityList.add(fpOrderEntity);
    }

    public FinishedProductEntity(String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList) { // 테스트용
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpImage = fpImage;
        this.fpPrice = fpPrice;
        this.fpState = fpState;
        this.fpFlowerList = fpFlowerList;
    }

//    public FinishedProductEntity(String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList) {
//        FlowerShopRepository flowerShopRepo = null;
//
//        Optional<FlowerShopEntity> flowerShopEntity = flowerShopRepo.findByshopKey(shopKey);
//
//        this.flowerShopEntity = shopKey;
//        this.fpName = fpName;
//        this.fpTag = fpTag;
//        this.fpImage = fpImage;
//        this.fpPrice = fpPrice;
//        this.fpState = fpState;
//        this.fpFlowerList = fpFlowerList;
//    }

    public FinishedProductEntity() {
    }

    public static FinishedProductEntity finishedProductDtotoEntity(FinishedProductDto finishedProductDto)
    {
        FinishedProductEntity finishedProductEntity = new FinishedProductEntity();
            if(finishedProductDto.getFpImage()!=null)
            {
                finishedProductEntity.setFpImage(finishedProductDto.getFpImage());
                System.out.println("여긴 DtoToEntity내부 fpImage여부를 확인"+finishedProductEntity.getFpImage()+"---------------");
            }
            else {
                System.out.println("DtoToEntity내부 fpImage가 비어있습니다 ******************************");
            }
        finishedProductEntity.setFpName(finishedProductDto.getFpName());
        finishedProductEntity.setFpTag(finishedProductDto.getFpTag());
        finishedProductEntity.setFpPrice(finishedProductDto.getFpPrice());
        finishedProductEntity.setFpDetail(finishedProductDto.getFpDetail());
        finishedProductEntity.setFpFlowerList(finishedProductDto.getFpFlowerList().split(","));
        return finishedProductEntity;
    }
}
