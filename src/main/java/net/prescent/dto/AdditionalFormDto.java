package net.prescent.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdditionalFormDto {
    private String title;
    private List<String> items;
}
