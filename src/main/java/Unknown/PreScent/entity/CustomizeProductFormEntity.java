package Unknown.PreScent.entity;

import Unknown.PreScent.dto.AdditionalFormDto;
import Unknown.PreScent.dto.CustomizeProductFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customize-product-form")
public class CustomizeProductFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cpKey;
    private List<Integer> price;
    private List<AdditionalFormDto> additionalForm;


    public static CustomizeProductFormEntity customizeProductFormDtoToEntity(CustomizeProductFormDto customizeProductFormDto){
        CustomizeProductFormEntity customizeProductFormEntity = new CustomizeProductFormEntity();
        customizeProductFormEntity.setCpKey(customizeProductFormDto.getCpKey());
        customizeProductFormEntity.setPrice(customizeProductFormDto.getPrice());
        customizeProductFormEntity.setAdditionalForm(customizeProductFormDto.getAdditionalForm());

        return customizeProductFormEntity;
    }

}
