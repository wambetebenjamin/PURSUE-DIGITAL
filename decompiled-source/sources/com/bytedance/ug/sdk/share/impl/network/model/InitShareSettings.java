package com.bytedance.ug.sdk.share.impl.network.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class InitShareSettings {
    @SerializedName("album_parse_switch")
    private int mAlbumParseSwitch = 1;
    @SerializedName("qrcode_parse_switch")
    private int mQrcodeParseSwitch = 1;
    @SerializedName("hidden_mark_parse_switch")
    private int mHiddenMarkParseSwitch = 1;
    @SerializedName("token_parse_switch")
    private int mTextTokenParseSwitch = 1;
    @SerializedName("video_hidden_mark_switch")
    private int mVideoHiddenMarkSwitch = 1;
    @SerializedName("video_qrcode_switch")
    private int mVideoQrcodeSwitch = 1;

    public int getAlbumParseSwitch() {
        return this.mAlbumParseSwitch;
    }

    public void setAlbumParseSwitch(int i2) {
        this.mAlbumParseSwitch = i2;
    }

    public int getQrcodeParseSwitch() {
        return this.mQrcodeParseSwitch;
    }

    public void setQrcodeParseSwitch(int i2) {
        this.mQrcodeParseSwitch = i2;
    }

    public int getHiddenMarkParseSwitch() {
        return this.mHiddenMarkParseSwitch;
    }

    public void setHiddenMarkParseSwitch(int i2) {
        this.mHiddenMarkParseSwitch = i2;
    }

    public int getTextTokenParseSwitch() {
        return this.mTextTokenParseSwitch;
    }

    public void setTextTokenParseSwitch(int i2) {
        this.mTextTokenParseSwitch = i2;
    }

    public int getVideoHiddenMarkSwitch() {
        return this.mVideoHiddenMarkSwitch;
    }

    public void setVideoHiddenMarkSwitch(int i2) {
        this.mVideoHiddenMarkSwitch = i2;
    }

    public int getVideoQrcodeSwitch() {
        return this.mVideoQrcodeSwitch;
    }

    public void setVideoQrcodeSwitch(int i2) {
        this.mVideoQrcodeSwitch = i2;
    }
}
