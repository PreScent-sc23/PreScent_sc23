package net.prescent.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class getDateDto {

    @Min(value = 1, message = "월이 1보다 작습니다.")
    @Max(value = 12, message = "월이 12월보다 큽니다.")
    private Integer month;

    @Min(value = 2023, message = "연도가 2023보다 작습니다.")
    private Integer year;

    @NotBlank(message = "예약이 없습니다.")
    private String reserve;
}
