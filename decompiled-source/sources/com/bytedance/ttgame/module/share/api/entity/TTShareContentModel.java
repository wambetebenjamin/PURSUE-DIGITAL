package com.bytedance.ttgame.module.share.api.entity;

import android.graphics.Bitmap;
import com.bytedance.ttgame.module.share.api.entity.TTShareTapTapExtendModel;
import com.bytedance.ttgame.module.share.api.panel.TTShareItemType;
import com.bytedance.ttgame.module.share.api.utils.FileUtils;
/* loaded from: classes7.dex */
public class TTShareContentModel {
    private String mDesc;
    private Bitmap mImage;
    private String mImageCode;
    private String mImagePath;
    private String mImageUrl;
    private TTShareContentType mShareContentType;
    private TTShareDouYinExtendModel mShareDouYinExtendModel;
    private TTShareTapTapExtendModel mShareTapTapExtendModel;
    private TTShareItemType mShareType;
    private Bitmap mThumbImage;
    private String mTitle;
    private String mVideoPath;
    private String mVideoUrl;
    private TTWBExtra mWBExtra;
    private String mWebPageUrl;

    public String getImageCode() {
        return this.mImageCode;
    }

    public TTWBExtra getWBExtra() {
        return this.mWBExtra;
    }

    public void setWBExtra(TTWBExtra tTWBExtra) {
        this.mWBExtra = tTWBExtra;
    }

    private TTShareContentModel() {
        this.mShareContentType = TTShareContentType.ALL;
    }

    public TTShareItemType getShareType() {
        return this.mShareType;
    }

    public void setShareType(TTShareItemType tTShareItemType) {
        this.mShareType = tTShareItemType;
    }

    public TTShareContentType getShareContentType() {
        return this.mShareContentType;
    }

    public void setShareContentType(TTShareContentType tTShareContentType) {
        this.mShareContentType = tTShareContentType;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getImagePath() {
        return this.mImagePath;
    }

    public String getVideoPath() {
        return this.mVideoPath;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }

    public Bitmap getThumbImage() {
        return this.mThumbImage;
    }

    public void setThumbImage(Bitmap bitmap) {
        this.mThumbImage = bitmap;
    }

    public String getVideoUrl() {
        return this.mVideoUrl;
    }

    public void setVideoUrl(String str) {
        this.mVideoUrl = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getWebPageUrl() {
        return this.mWebPageUrl;
    }

    public void setWebPageUrl(String str) {
        this.mWebPageUrl = str;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public Bitmap getImage() {
        return this.mImage;
    }

    public void setImage(Bitmap bitmap) {
        this.mImage = bitmap;
    }

    public TTShareDouYinExtendModel getShareDouYinExtendModel() {
        TTShareDouYinExtendModel tTShareDouYinExtendModel = this.mShareDouYinExtendModel;
        return tTShareDouYinExtendModel == null ? new TTShareDouYinExtendModel() : tTShareDouYinExtendModel;
    }

    public void setShareDouYinExtendModel(TTShareDouYinExtendModel tTShareDouYinExtendModel) {
        this.mShareDouYinExtendModel = tTShareDouYinExtendModel;
    }

    public void setShareTapTapExtendModel(TTShareTapTapExtendModel tTShareTapTapExtendModel) {
        this.mShareTapTapExtendModel = tTShareTapTapExtendModel;
    }

    public TTShareTapTapExtendModel getShareTapTapExtendModel() {
        TTShareTapTapExtendModel tTShareTapTapExtendModel = this.mShareTapTapExtendModel;
        return tTShareTapTapExtendModel == null ? new TTShareTapTapExtendModel.Builder().build() : tTShareTapTapExtendModel;
    }

    public String toString() {
        return "TTShareContentModel{mShareType=" + this.mShareType + ", mShareContentType=" + this.mShareContentType + ", mImageUrl='" + this.mImageUrl + "', mImagePath='" + this.mImagePath + "', mThumbImage=" + this.mThumbImage + ", mVideoUrl='" + this.mVideoUrl + "', mVideoPath='" + this.mVideoPath + "', mTitle='" + this.mTitle + "', mWebPageUrl='" + this.mWebPageUrl + "', mDesc='" + this.mDesc + "', mImage=" + this.mImage + ", mImageCode='" + this.mImageCode + "', mShareDouYinExtendModel=" + this.mShareDouYinExtendModel + ", mWBExtra=" + this.mWBExtra + ", mShareTapTapExtendModel=" + this.mShareTapTapExtendModel + '}';
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        private TTShareContentModel mShareContentModel = new TTShareContentModel();

        public Builder setShareType(TTShareItemType tTShareItemType) {
            this.mShareContentModel.mShareType = tTShareItemType;
            return this;
        }

        public Builder setShareContentType(TTShareContentType tTShareContentType) {
            this.mShareContentModel.mShareContentType = tTShareContentType;
            return this;
        }

        public Builder setImageUrl(String str) {
            if (FileUtils.INSTANCE.isUrl(str)) {
                this.mShareContentModel.mImageUrl = str;
            } else {
                this.mShareContentModel.mImagePath = str;
            }
            return this;
        }

        public Builder setImagePath(String str) {
            this.mShareContentModel.mImagePath = str;
            return this;
        }

        public Builder setThumbImage(Bitmap bitmap) {
            this.mShareContentModel.mThumbImage = bitmap;
            return this;
        }

        public Builder setVideoUrl(String str) {
            if (FileUtils.INSTANCE.isUrl(str)) {
                this.mShareContentModel.mVideoUrl = str;
            } else {
                this.mShareContentModel.mVideoPath = str;
            }
            return this;
        }

        public Builder setVideoPath(String str) {
            this.mShareContentModel.mVideoPath = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.mShareContentModel.mTitle = str;
            return this;
        }

        public Builder setWebPageUrl(String str) {
            this.mShareContentModel.mWebPageUrl = str;
            return this;
        }

        public Builder setDesc(String str) {
            this.mShareContentModel.mDesc = str;
            return this;
        }

        public Builder setImage(Bitmap bitmap) {
            this.mShareContentModel.mImage = bitmap;
            return this;
        }

        public Builder setImageCode(String str) {
            this.mShareContentModel.mImageCode = str;
            return this;
        }

        public Builder setShareDouYinExtendModel(TTShareDouYinExtendModel tTShareDouYinExtendModel) {
            this.mShareContentModel.mShareDouYinExtendModel = tTShareDouYinExtendModel;
            return this;
        }

        public Builder setShareTapTapExtendModel(TTShareTapTapExtendModel tTShareTapTapExtendModel) {
            this.mShareContentModel.mShareTapTapExtendModel = tTShareTapTapExtendModel;
            return this;
        }

        public Builder setWBExtra(TTWBExtra tTWBExtra) {
            this.mShareContentModel.mWBExtra = tTWBExtra;
            return this;
        }

        public TTShareContentModel build() {
            return this.mShareContentModel;
        }
    }
}
