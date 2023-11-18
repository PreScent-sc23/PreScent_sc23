package Unknown.PreScent.dto;

import Unknown.PreScent.entity.CustomerEntity;
import Unknown.PreScent.entity.FinishedProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FPOrderSellerDto {
    @NotNull
    private Integer fpOrderKey;
    @NotNull
    private Byte fpOrderState;
}
