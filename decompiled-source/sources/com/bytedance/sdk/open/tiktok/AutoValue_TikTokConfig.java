package com.bytedance.sdk.open.tiktok;

import com.bytedance.sdk.open.tiktok.TikTokConfig;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_TikTokConfig extends TikTokConfig {
    private final String clientKey;

    private AutoValue_TikTokConfig(String str) {
        this.clientKey = str;
    }

    @Override // com.bytedance.sdk.open.tiktok.TikTokConfig
    public String getClientKey() {
        return this.clientKey;
    }

    public String toString() {
        return "TikTokConfig{clientKey=" + this.clientKey + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TikTokConfig) {
            return this.clientKey.equals(((TikTokConfig) obj).getClientKey());
        }
        return false;
    }

    public int hashCode() {
        return this.clientKey.hashCode() ^ 1000003;
    }

    /* loaded from: classes3.dex */
    static final class Builder extends TikTokConfig.Builder {
        private String clientKey;

        @Override // com.bytedance.sdk.open.tiktok.TikTokConfig.Builder
        public TikTokConfig.Builder clientKey(String str) {
            Objects.requireNonNull(str, "Null clientKey");
            this.clientKey = str;
            return this;
        }

        @Override // com.bytedance.sdk.open.tiktok.TikTokConfig.Builder
        public TikTokConfig build() {
            String str = this.clientKey == null ? " clientKey" : "";
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_TikTokConfig(this.clientKey);
        }
    }
}
