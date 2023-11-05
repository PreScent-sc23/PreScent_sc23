package Unknown.PreScent.dto;

import Unknown.PreScent.entity.SellerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SellerDto {

    private Long sellerKey;    //system ID
    private String sellerName;
    private String ID;
    private String password;
    private String sellerPhoneNum;

    public static SellerDto toSellerDto(SellerEntity sellerEntity){
        SellerDto sellerDto = new SellerDto();

        sellerDto.setSellerKey(sellerEntity.getSellerKey());
        sellerDto.setSellerName(sellerEntity.getSellerName());
        sellerDto.setSellerPhoneNum(sellerEntity.getSellerPhoneNum());
        sellerDto.setID(sellerEntity.getID());
        sellerDto.setPassword(sellerEntity.getPassword());
        return sellerDto;
    }
    /*
    public Long getSellerKey() {
        return sellerKey;
    }

    public void setSellerKey(Long sellerKey) {
        this.sellerKey = sellerKey;
    }
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSellerPhoneNum() {
        return sellerPhoneNum;
    }

    public void setSellerPhoneNum(String sellerPhoneNum) {
        this.sellerPhoneNum = sellerPhoneNum;
    }
     */

}
