package net.prescent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

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
