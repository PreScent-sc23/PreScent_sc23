package Unknown.PreScent.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CustomerLoginRequestDto {
    @NotBlank
    private String sellerIdEmail;
    @NotBlank
    private String sellerPassword;
}
