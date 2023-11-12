package Unknown.PreScent.entity;

import Unknown.PreScent.dto.SellerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "seller")
public class SellerEntity {
    @Id
    @Column(nullable = false)
    private Integer sellerKey;
    @Column(nullable = false)
    private String sellerName;
    @Column(nullable = false, unique = true)
    private String sellerIdEmail;
    @Column(nullable = false)
    private String sellerPassword;
    @Column(nullable = false)
    private String sellerPhonenum;
    private Integer isgrant;

    @OneToOne
    @JoinColumn(name = "shopKey")
    private FlowerShopEntity flowerShopEntity;

    public void setFlowerShopEntity(FlowerShopEntity flowerShopEntity)
    {
        this.flowerShopEntity = flowerShopEntity;
        // this.flowerShopEntity.setSellerEntity(this);
    }
    public static SellerEntity toSellerEntity(SellerDto sellerDto){
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerKey(sellerDto.getSellerKey());
        sellerEntity.setSellerName(sellerDto.getSellerName());
        sellerEntity.setSellerPhonenum(sellerDto.getSellerPhonenum());
        sellerEntity.setSellerIdEmail(sellerDto.getSellerIdEmail());
        sellerEntity.setSellerPassword(sellerDto.getSellerPassword());
        sellerEntity.setIsgrant(sellerDto.getIsgrant());
        return sellerEntity;
    }
}
