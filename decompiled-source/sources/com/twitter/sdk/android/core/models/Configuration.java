package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.MediaEntity;
import java.util.List;
/* loaded from: classes10.dex */
public class Configuration {
    @SerializedName("dm_text_character_limit")
    public final int dmTextCharacterLimit;
    @SerializedName("non_username_paths")
    public final List<String> nonUsernamePaths;
    @SerializedName("photo_size_limit")
    public final long photoSizeLimit;
    @SerializedName("photo_sizes")
    public final MediaEntity.Sizes photoSizes;
    @SerializedName("short_url_length_https")
    public final int shortUrlLengthHttps;

    private Configuration() {
        this(0, null, 0L, null, 0);
    }

    public Configuration(int i2, List<String> list, long j2, MediaEntity.Sizes sizes, int i3) {
        this.dmTextCharacterLimit = i2;
        this.nonUsernamePaths = ModelUtils.getSafeList(list);
        this.photoSizeLimit = j2;
        this.photoSizes = sizes;
        this.shortUrlLengthHttps = i3;
    }
}
