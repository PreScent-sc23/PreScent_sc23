package Unknown.PreScent.entity;

import Unknown.PreScent.dto.SellerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "seller")
public class SellerEntity {
    @Id
    @Column(nullable = false)
    private Long sellerKey;
    @Column(nullable = false)
    private String sellerName;
    @Column(nullable = false, unique = true)
    private String sellerIdEmail;
    @Column(nullable = false)
    private String sellerPassword;
    @Column(nullable = false)
    private String sellerPhonenum;
    private Integer isgrant;

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
