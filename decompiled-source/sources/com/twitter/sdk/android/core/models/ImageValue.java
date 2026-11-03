package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class ImageValue {
    @SerializedName("alt")
    public final String alt;
    @SerializedName("height")
    public final int height;
    @SerializedName("url")
    public final String url;
    @SerializedName("width")
    public final int width;

    public ImageValue(int i2, int i3, String str, String str2) {
        this.height = i2;
        this.width = i3;
        this.url = str;
        this.alt = str2;
    }
}
