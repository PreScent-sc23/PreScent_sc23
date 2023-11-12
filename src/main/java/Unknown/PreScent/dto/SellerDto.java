package Unknown.PreScent.dto;

import Unknown.PreScent.entity.SellerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SellerDto {

    @NotBlank(message = "사업자등록번호를 작성해주세요")
    private Integer sellerKey;
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
}
