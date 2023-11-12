package Unknown.PreScent.entity;

import Unknown.PreScent.dto.FinishedProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "finished_product")
public class FinishedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fpKey;

    @Column(nullable = false)
    private Integer shopKey;

    @Column(nullable = false)
    private String fpName;

    private String fpTag;

    private String fpImage;
    @Column(nullable = false)
    private Integer fpPrice;
    @Column(nullable = false)
    private boolean fpState;

    private String[] fpFlowerList;


    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;


//    @ManyToOne
//    @JoinColumn(name = "FlowerShop_shopKey")
//    private FlowerShopEntity flowerShopEntity;

    public FinishedProductEntity(String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList) {
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpImage = fpImage;
        this.fpPrice = fpPrice;
        this.fpState = fpState;
        this.fpFlowerList = fpFlowerList;
    }
    public FinishedProductEntity(Integer shopKey, String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList) {
        this.shopKey = shopKey;
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpImage = fpImage;
        this.fpPrice = fpPrice;
        this.fpState = fpState;
        this.fpFlowerList = fpFlowerList;
    }

    public FinishedProductEntity() {
    }

    public static FinishedProductEntity toFinishedProductEntity(FinishedProductDto finishedProductDto)
    {
        FinishedProductEntity finishedProductEntity = new FinishedProductEntity();
        finishedProductEntity.setFpKey(finishedProductDto.getFpKey());
        finishedProductEntity.setShopKey(finishedProductDto.getShopKey());
        finishedProductEntity.setFpName(finishedProductDto.getFpName());
        finishedProductEntity.setFpTag(finishedProductDto.getFpTag());
        finishedProductEntity.setFpImage(finishedProductDto.getFpImage());
        finishedProductEntity.setFpPrice(finishedProductDto.getFpPrice());
        finishedProductEntity.setFpState(finishedProductDto.isFpState());
        finishedProductEntity.setFpFlowerList(finishedProductDto.getFpFlowerList());
        return finishedProductEntity;
    }
}
