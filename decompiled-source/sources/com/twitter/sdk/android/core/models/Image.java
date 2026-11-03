package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
/* loaded from: classes10.dex */
public class Image {
    @SerializedName("h")

    /* renamed from: h  reason: collision with root package name */
    public final int f18798h;
    @SerializedName("image_type")
    public final String imageType;
    @SerializedName(DownloadFileUtils.MODE_WRITE)
    public final int w;

    public Image(int i2, int i3, String str) {
        this.w = i2;
        this.f18798h = i3;
        this.imageType = str;
    }
}
