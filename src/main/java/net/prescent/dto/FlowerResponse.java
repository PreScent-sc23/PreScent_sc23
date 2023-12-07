package net.prescent.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlowerResponse {
    private List<FlowerDto> flowers;
    private List<String> croppedImagePaths;
}
