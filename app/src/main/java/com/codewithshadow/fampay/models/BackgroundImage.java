package com.codewithshadow.fampay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BackgroundImage {


    @SerializedName("image_type")
    @Expose
    private String image_type;

    @SerializedName("image_url")
    @Expose
    private String image_url;

    @SerializedName("aspect_ratio")
    @Expose
    private long aspect_ratio;

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public long getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(long aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }
}
