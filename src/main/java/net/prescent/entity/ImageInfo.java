package net.prescent.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageInfo {
    private String cropImage;
    private String name;
    private String meaning;

    public ImageInfo(String cropImage, String name, String meaning) {
        this.cropImage = cropImage;
        this.name = name;
        this.meaning = meaning;
    }
}
