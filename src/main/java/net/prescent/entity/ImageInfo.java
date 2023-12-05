package net.prescent.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageInfo {
    private String url;
    private String name;
    private String meaning;

    public ImageInfo(String url, String name, String meaning) {
        this.url = url;
        this.name = name;
        this.meaning = meaning;
    }
}
