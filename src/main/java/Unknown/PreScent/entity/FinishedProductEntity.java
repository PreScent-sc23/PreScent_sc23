package Unknown.PreScent.entity;

import Unknown.PreScent.dto.FinishedProductDto;
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
    private Integer shopKey;

    @Column(nullable = false)
    private String fpName;
    private String fpTag;
    @Lob
    @Column(name = "fpImage", columnDefinition="BLOB")
    private byte[] fpImage;
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
    private List<FPOrderEntity> fpOrderEntityList;

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
    public FinishedProductEntity(String fpName, String fpTag, byte[] fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList) {
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpImage = fpImage;
        this.fpPrice = fpPrice;
        this.fpState = fpState;
        this.fpFlowerList = fpFlowerList;
    }
    public FinishedProductEntity(Integer shopKey, String fpName, String fpTag, byte[] fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList) {
        this.shopKey = shopKey;
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpImage = fpImage;
        this.fpPrice = fpPrice;
        this.fpState = fpState;
        this.fpFlowerList = fpFlowerList;
    }

    public FinishedProductEntity(Integer shopKey, String fpName, String fpTag, Integer fpPrice, String[] fpFlowerList, String fpDetail) {
        this.shopKey = shopKey;
        this.fpName = fpName;
        this.fpTag = fpTag;
        this.fpPrice = fpPrice;
        this.fpFlowerList = fpFlowerList;
        this.fpDetail = fpDetail;
    }

    public FinishedProductEntity() {
    }

    public static FinishedProductEntity finishedProductDtotoEntity(FinishedProductDto finishedProductDto)
    {
        FinishedProductEntity finishedProductEntity = new FinishedProductEntity();
        try {
            if(finishedProductDto.getFpImage()!=null)
            {finishedProductEntity.setFpImage(finishedProductDto.getFpImage().getBytes());}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finishedProductEntity.setFpName(finishedProductDto.getFpName());
        finishedProductEntity.setFpTag(finishedProductDto.getFpTag());
        finishedProductEntity.setFpPrice(finishedProductDto.getFpPrice());
        finishedProductEntity.setFpFlowerList(finishedProductDto.getFpFlowerList());
        return finishedProductEntity;
    }
}
