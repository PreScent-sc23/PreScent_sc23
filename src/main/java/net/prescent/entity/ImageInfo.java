package net.prescent.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageInfo {
    private String url;
    private String flowerName;
    private String flowerDescription;

    public ImageInfo(String url, String flowerName, String flowerDescription) {
        this.url = url;
        this.flowerName = flowerName;
        this.flowerDescription = flowerDescription;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "url='" + url + '\'' +
                ", flowerName='" + flowerName + '\'' +
                ", flowerDescription='" + flowerDescription + '\'' +
                '}';
    }
}
