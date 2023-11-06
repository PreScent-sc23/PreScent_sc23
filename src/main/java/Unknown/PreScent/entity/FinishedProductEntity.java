package Unknown.PreScent.entity;

import javax.persistence.*;

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
    private boolean fpState=false;

    private String[] fpFlowerList;
    @ManyToOne
    @JoinColumn(name = "FlowerShop_shopKey")
    private FlowerShopEntity flowerShopEntity;

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

    public Integer getFpKey() {
        return fpKey;
    }

    public void setFpKey(Integer fpKey) {
        this.fpKey = fpKey;
    }

    public Integer getShopKey() {
        return shopKey;
    }

    public void setShopKey(Integer shopKey) {
        this.shopKey = shopKey;
    }

    public String getFpName() {
        return fpName;
    }

    public void setFpName(String fpName) {
        this.fpName = fpName;
    }

    public String getFpTag() {
        return fpTag;
    }

    public void setFpTag(String fpTag) {
        this.fpTag = fpTag;
    }

    public String getFpImage() {
        return fpImage;
    }

    public void setFpImage(String fpImage) {
        this.fpImage = fpImage;
    }

    public Integer getFpPrice() {
        return fpPrice;
    }

    public void setFpPrice(Integer fpPrice) {
        this.fpPrice = fpPrice;
    }

    public boolean isFpState() {
        return fpState;
    }

    public void setFpState(boolean fpState) {
        this.fpState = fpState;
    }

    public String[] getFpFlowerList() {
        return fpFlowerList;
    }

//    public void setFpFlowerList(String[] fpFlowerList) {
//        this.fpFlowerList = fpFlowerList;
//    }
}
