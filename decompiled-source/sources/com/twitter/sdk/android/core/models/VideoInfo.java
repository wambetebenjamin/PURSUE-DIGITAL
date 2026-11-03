package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes10.dex */
public class VideoInfo implements Serializable {
    @SerializedName("aspect_ratio")
    public final List<Integer> aspectRatio;
    @SerializedName("duration_millis")
    public final long durationMillis;
    @SerializedName("variants")
    public final List<Variant> variants;

    private VideoInfo() {
        this(null, 0L, null);
    }

    public VideoInfo(List<Integer> list, long j2, List<Variant> list2) {
        this.aspectRatio = ModelUtils.getSafeList(list);
        this.durationMillis = j2;
        this.variants = ModelUtils.getSafeList(list2);
    }

    /* loaded from: classes10.dex */
    public static class Variant implements Serializable {
        @SerializedName("bitrate")
        public final long bitrate;
        @SerializedName("content_type")
        public final String contentType;
        @SerializedName("url")
        public final String url;

        public Variant(long j2, String str, String str2) {
            this.bitrate = j2;
            this.contentType = str;
            this.url = str2;
        }
    }
}
