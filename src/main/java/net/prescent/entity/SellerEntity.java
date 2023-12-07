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
    private Long businessKey;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String idEmail;
    @Column(nullable = false)
    private String phonenum;
    private Integer isgrant;

    @OneToOne
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
        sellerEntity.setName(sellerDto.getName());
        sellerEntity.setPhonenum(sellerDto.getPhonenum());
        sellerEntity.setIdEmail(sellerDto.getIdEmail());
        sellerEntity.setPassword(sellerDto.getPassword());
        sellerEntity.setIsgrant(sellerDto.getIsgrant());
        return sellerEntity;
    }

    @Override
    public String getUserType() {
        return "Seller";
    }
}
