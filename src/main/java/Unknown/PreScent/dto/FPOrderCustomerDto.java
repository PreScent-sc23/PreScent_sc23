package Unknown.PreScent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class FPOrderCustomerDto {

    @NotNull
    private Integer fpKey;
    @NotNull
    private Integer customerKey;
    private String purchaseInfo;
    @NotNull
    private Date pickupDate;
}
