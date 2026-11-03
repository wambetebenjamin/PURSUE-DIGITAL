package com.bytedance.sdk.open.tiktok;

import com.bytedance.sdk.open.tiktok.AutoValue_TikTokConfig;
/* loaded from: classes3.dex */
public abstract class TikTokConfig {

    /* loaded from: classes3.dex */
    public static abstract class Builder {
        public abstract TikTokConfig build();

        public abstract Builder clientKey(String str);
    }

    public abstract String getClientKey();

    public static Builder builder() {
        return new AutoValue_TikTokConfig.Builder();
    }
}
