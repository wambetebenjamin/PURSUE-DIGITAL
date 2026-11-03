package com.bytedance.ttgame.module.share.api.entity;

import android.graphics.Bitmap;
/* loaded from: classes7.dex */
public class TTTokenShareInfo {
    private String description;
    private Bitmap mImage;
    private String mImageUrl;
    private String tips;
    private String title;

    public Bitmap getImage() {
        return this.mImage;
    }

    public void setImage(Bitmap bitmap) {
        this.mImage = bitmap;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String str) {
        this.tips = str;
    }
}
