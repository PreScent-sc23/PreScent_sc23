package net.prescent.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SellerLoginRequestDto {
    @NotBlank
    private String sellerIdEmail;
    @NotBlank
    private String sellerPassword;
}
