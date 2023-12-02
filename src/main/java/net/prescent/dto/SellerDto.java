package net.prescent.dto;

import lombok.*;
import net.prescent.entity.SellerEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "password")
public class SellerDto {


    private Integer userKey;
    //@NotBlank(message = "사업자등록번호를 작성해주세요")
    private Long businessKey;
    @NotBlank(message = "이름을 작성해주세요")
    private String name;
    @NotBlank(message = "이메일을 작성해주세요")
    @Email(message = "올바른 이메일 형식을 사용해주세요")
    private String idEmail;
    @NotBlank(message = "비밀번호를 작성해주세요")
    private String password;
    private String confirmPassword;
    @NotBlank(message = "전화번호를 작성해주세요")
    private String phonenum;
    private Integer isgrant;

    public static SellerDto toSellerDto(SellerEntity sellerEntity) {
        SellerDto sellerDto = new SellerDto();

        sellerDto.setBusinessKey(sellerEntity.getBusinessKey());
        sellerDto.setName(sellerEntity.getName());
        sellerDto.setPhonenum(sellerEntity.getPhonenum());
        sellerDto.setIdEmail(sellerEntity.getIdEmail());
        sellerDto.setIsgrant(sellerEntity.getIsgrant());
        return sellerDto;
    }
}
