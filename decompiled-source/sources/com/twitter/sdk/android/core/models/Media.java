package com.twitter.sdk.android.core.models;

import com.bytedance.apm.constant.i;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class Media {
    @SerializedName("image")
    public final Image image;
    @SerializedName("media_id")
    public final long mediaId;
    @SerializedName("media_id_string")
    public final String mediaIdString;
    @SerializedName(i.PERF_DISK_FILE_SIZE)
    public final long size;

    public Media(long j2, String str, long j3, Image image) {
        this.mediaId = j2;
        this.mediaIdString = str;
        this.size = j3;
        this.image = image;
    }
}
