package com.shree.ecommerce_m_v.utils.imageUploader.domain;

import java.io.Serializable;

public class ImageUrlResponse implements Serializable {
    private String imageUrl;
    private String thumbnailUrl;

    public ImageUrlResponse() {
    }

    public ImageUrlResponse(String imageUrl, String thumbnailUrl) {
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public ImageUrlResponse(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
