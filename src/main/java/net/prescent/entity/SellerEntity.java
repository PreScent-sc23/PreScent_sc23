package net.prescent.entity;

import net.prescent.dto.SellerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "seller")
public class SellerEntity extends UserEntity {

    @Column(nullable = false)
    private Integer businessKey;
    @Column(nullable = false)
    private String sellerName;
    @Column(nullable = false, unique = true)
    private String sellerIdEmail;
    @Column(nullable = false)
    private String sellerPassword;
    @Column(nullable = false)
    private String sellerPhonenum;
    private Integer isgrant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopKey", referencedColumnName = "shopKey")
    private FlowerShopEntity flowerShopEntity;

    public void setFlowerShopEntity(FlowerShopEntity flowerShopEntity)
    {
        this.flowerShopEntity = flowerShopEntity;
        // this.flowerShopEntity.setSellerEntity(this);
    }
    public static SellerEntity toSellerEntity(SellerDto sellerDto){
        SellerEntity sellerEntity = new SellerEntity();

        sellerEntity.setBusinessKey(sellerDto.getBusinessKey());
        sellerEntity.setSellerName(sellerDto.getSellerName());
        sellerEntity.setSellerPhonenum(sellerDto.getSellerPhonenum());
        sellerEntity.setSellerIdEmail(sellerDto.getSellerIdEmail());
        sellerEntity.setSellerPassword(sellerDto.getSellerPassword());
        sellerEntity.setIsgrant(sellerDto.getIsgrant());
        return sellerEntity;
    }

    @Override
    public String getUserType() {
        return "Seller";
    }
}
