package net.prescent.dto;

import lombok.Getter;
import lombok.Setter;
import net.prescent.entity.FlowerShopEntity;

import java.util.List;

@Getter
@Setter
public class CustomizeProductDto {

    private Integer cpKey;
    private String[] answers;

    private FlowerShopEntity flowerShopEntity;


}
