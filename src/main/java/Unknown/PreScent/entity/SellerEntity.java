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
    @Column(name = "seller_key")
    private Long sellerKey;
    @Column(name = "seller_name")
    private String sellerName;
    @Column(name = "seller_id")
    private String sellerId;
    @Column(name = "seller_password")
    private String sellerPassword;
    @Column(name = "seller_phonenum")
    private String sellerPhonenum;

    public static SellerEntity toSellerEntity(SellerDto sellerDto){
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerKey(sellerDto.getSellerKey());
        sellerEntity.setSellerName(sellerDto.getSellerName());
        sellerEntity.setSellerPhonenum(sellerDto.getSellerPhonenum());
        sellerEntity.setSellerId(sellerDto.getSellerId());
        sellerEntity.setSellerPassword(sellerDto.getSellerPassword());
        return sellerEntity;
    }
}
