package com.bytedance.ug.sdk.share.impl.network.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class ShareDetail implements Serializable {
    @SerializedName("audio_url")
    private String mAudioUrl;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("extra")
    private String mExtra;
    @SerializedName("hidden_url")
    private String mHiddenImageUrl;
    @SerializedName("image_token_info")
    private TokenInfo mImageTokenInfo;
    @SerializedName("thumb_image_url")
    private String mImageUrl;
    @SerializedName("qrcode_url")
    private String mQrCodeImageUrl;
    @SerializedName("share_url")
    private String mShareUrl;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("token_info")
    private TokenInfo mTokenInfo;
    @SerializedName("video_url")
    private String mVideoUrl;

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

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }

    public String getShareUrl() {
        return this.mShareUrl;
    }

    public void setShareUrl(String str) {
        this.mShareUrl = str;
    }

    public TokenInfo getTokenInfo() {
        return this.mTokenInfo;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.mTokenInfo = tokenInfo;
    }

    public TokenInfo getImageTokenInfo() {
        return this.mImageTokenInfo;
    }

    public void setImageTokenInfo(TokenInfo tokenInfo) {
        this.mImageTokenInfo = tokenInfo;
    }

    public String getQrCodeImageUrl() {
        return this.mQrCodeImageUrl;
    }

    public void setQrCodeImageUrl(String str) {
        this.mQrCodeImageUrl = str;
    }

    public String getHiddenImageUrl() {
        return this.mHiddenImageUrl;
    }

    public void setHiddenImageUrl(String str) {
        this.mHiddenImageUrl = str;
    }

    public String getAudioUrl() {
        return this.mAudioUrl;
    }

    public void setAudioUrl(String str) {
        this.mAudioUrl = str;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public void setExtra(String str) {
        this.mExtra = str;
    }
}
