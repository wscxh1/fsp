package com.foodshare.entity;

import java.io.Serializable;

public class Image  implements Serializable {
    private String md5;

    private String date;

    private String postfix;

    private String thumbnailMd5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix == null ? null : postfix.trim();
    }

    public String getThumbnailMd5() {
        return thumbnailMd5;
    }

    public void setThumbnailMd5(String thumbnailMd5) {
        this.thumbnailMd5 = thumbnailMd5 == null ? null : thumbnailMd5.trim();
    }
}