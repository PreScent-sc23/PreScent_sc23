package Unknown.PreScent.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomizeProductFormDto {
    private Integer cpKey;
    private String title;
    private List<Integer> price;
    private String detail;
    private List<AdditionalFormDto> additionalForm;
}
