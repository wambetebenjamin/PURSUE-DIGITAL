package com.bytedance.pipo.sec;

import android.content.Context;
import com.google.firebase.ktx.BuildConfig;
/* loaded from: classes2.dex */
public class UserConfig {
    public final String appChannel;
    public final Context appContext;
    public final String appId;
    public final boolean enableDynamicKey;
    public final boolean isSandbox;
    public final String productFlavor;
    public final int region;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private static final int BRANCH_CHINA = 0;
        private static final int BRANCH_OTHER = 1;
        private static final String UNSPECIFIED_CHANNEL = "unspecified";
        private static final String UNSPECIFIED_FLAVOR = "unspecified";
        private final Context mAppContext;
        private final String mAppId;
        private int mRegion = 0;
        private boolean mIsSandbox = false;
        private boolean mEnableDynamicKey = false;
        private String mProductFlavor = BuildConfig.VERSION_NAME;
        private String mAppChannel = BuildConfig.VERSION_NAME;

        public Builder(Context context, String str) {
            this.mAppContext = context.getApplicationContext();
            this.mAppId = str;
        }

        public UserConfig build() {
            return new UserConfig(this);
        }

        public Builder setChannel(String str) {
            this.mAppChannel = str;
            return this;
        }

        public Builder setEnableDynamicKey(boolean z) {
            this.mEnableDynamicKey = z;
            return this;
        }

        public Builder setProductFlavor(String str) {
            this.mProductFlavor = str;
            return this;
        }

        public Builder setRegion(int i2) {
            this.mRegion = i2;
            return this;
        }

        public Builder setSandbox(boolean z) {
            this.mIsSandbox = z;
            return this;
        }
    }

    private UserConfig(Builder builder) {
        this.appContext = builder.mAppContext;
        this.appId = builder.mAppId;
        this.region = builder.mRegion;
        this.isSandbox = builder.mIsSandbox;
        this.appChannel = builder.mAppChannel;
        this.productFlavor = builder.mProductFlavor;
        this.enableDynamicKey = builder.mEnableDynamicKey;
    }
}
