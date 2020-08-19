package com.shree.ecommerce_m_v.utils.domain;

import java.io.Serializable;

public class ImageUrlResponse implements Serializable {
    private static final long serialVersionUID = 7447895456626784604L;
    private String imageUrl;
    private String thumbnailUrl;

    public ImageUrlResponse() {
    }

    public ImageUrlResponse(String imageUrl, String thumbnailUrl) {
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "ImageUrlResponse{" +
                "imageUrl='" + imageUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
