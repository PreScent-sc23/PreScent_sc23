package net.prescent.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemAddRequestDto {
    private Integer userKey;
    private Integer fpKey;
    private Integer amount;
    private String pickupDate;
    private String pickupTime;

    public CartItemAddRequestDto(Integer userKey, Integer fpKey, Integer amount, String pickupDate, String pickupTime)
    {
        this.userKey = userKey;
        this.fpKey = fpKey;
        this.amount = amount;
        this.pickupDate=pickupDate;
        this.pickupTime=pickupTime;
    }
}
