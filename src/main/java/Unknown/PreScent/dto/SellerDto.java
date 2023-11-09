package Unknown.PreScent.dto;

import Unknown.PreScent.entity.SellerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SellerDto {

    @NotBlank(message = "사업자등록번호를 작성해주세요")
    private Long sellerKey;
    @NotBlank(message = "이름을 작성해주세요")
    private String sellerName;
    @Email(message = "올바른 이메일 형식을 사용해주세요")
    @NotBlank(message = "이메일을 작성해주세요")
    private String sellerIdEmail;
    @NotBlank(message = "비밀번호를 작성해주세요")
    private String sellerPassword;
    @NotBlank(message = "전화번호를 작성해주세요")
    private String sellerPhonenum;
    private Integer isgrant;

    public static SellerDto toSellerDto(SellerEntity sellerEntity){
        SellerDto sellerDto = new SellerDto();

        sellerDto.setSellerKey(sellerEntity.getSellerKey());
        sellerDto.setSellerName(sellerEntity.getSellerName());
        sellerDto.setSellerPhonenum(sellerEntity.getSellerPhonenum());
        sellerDto.setSellerIdEmail(sellerEntity.getSellerIdEmail());
        sellerDto.setSellerPassword(sellerEntity.getSellerPassword());
        sellerDto.setIsgrant(sellerEntity.getIsgrant());
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
