package Unknown.PreScent.entity;

import Unknown.PreScent.dto.SellerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Seller")
public class SellerEntity {
    @Id
    @Column(name = "sellerKey")
    private Long sellerKey;
    @Column(name = "sellerName")
    private String sellerName;
    @Column(name = "ID", unique = true)
    private String ID;
    @Column(name = "password")
    private String password;
    @Column(name = "sellerPhoneNum")
    private String sellerPhoneNum;

    public static SellerEntity toSellerEntity(SellerDto sellerDto){
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerKey(sellerDto.getSellerKey());
        sellerEntity.setSellerName(sellerDto.getSellerName());
        sellerEntity.setSellerPhoneNum(sellerDto.getSellerPhoneNum());
        sellerEntity.setID(sellerDto.getID());
        sellerEntity.setPassword(sellerDto.getPassword());
        return sellerEntity;
    }
}
