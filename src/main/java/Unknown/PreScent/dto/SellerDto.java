package Unknown.PreScent.dto;

import Unknown.PreScent.entity.SellerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SellerDto {

    private Integer sellerKey;    //system ID
    private String sellerName;
    private String sellerId;
    private String sellerPassword;
    private String sellerPhonenum;

    public static SellerDto toSellerDto(SellerEntity sellerEntity){
        SellerDto sellerDto = new SellerDto();

        sellerDto.setSellerKey(sellerEntity.getSellerKey());
        sellerDto.setSellerName(sellerEntity.getSellerName());
        sellerDto.setSellerPhonenum(sellerEntity.getSellerPhonenum());
        sellerDto.setSellerId(sellerEntity.getSellerId());
        sellerDto.setSellerPassword(sellerEntity.getSellerPassword());
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
