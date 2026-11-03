package com.bytedance.gameprotect;

import android.content.Context;
import com.google.firebase.ktx.BuildConfig;
/* loaded from: classes2.dex */
public class UserConfig {

    /* renamed from: a  reason: collision with root package name */
    public final boolean[] f8835a;
    public final String appChannel;
    public final Context appContext;
    public final String appId;
    public final boolean isSandbox;
    public final String productFlavor;
    public final int region;
    public final boolean usePacketChannel;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private static final int BRANCH_CHINA = 0;
        private static final int BRANCH_OTHER = 1;
        private static final String UNSPECIFIED_CHANNEL = "unspecified";
        private static final String UNSPECIFIED_FLAVOR = "unspecified";
        private final Context mAppContext;
        private final String mAppId;

        /* renamed from: a  reason: collision with root package name */
        private boolean[] f8836a = {true, true, true, true, true, true, true, true, true};
        private int mRegion = 2;
        private boolean mIsSandbox = false;
        private boolean mUsePacketChannel = false;
        private String mProductFlavor = BuildConfig.VERSION_NAME;
        private String mAppChannel = BuildConfig.VERSION_NAME;

        public Builder(Context context, String str) {
            this.mAppContext = context.getApplicationContext();
            this.mAppId = str;
        }

        public Builder a(int i2) {
            if (i2 > 0 && i2 < 8) {
                this.f8836a[i2] = false;
            }
            return this;
        }

        public UserConfig build() {
            return new UserConfig(this);
        }

        public Builder setChannel(String str) {
            this.mAppChannel = str;
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

        public Builder setUsePacketChannel(boolean z) {
            this.mUsePacketChannel = z;
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final int f8837a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f8838b = 1;

        /* renamed from: c  reason: collision with root package name */
        public static final int f8839c = 2;

        /* renamed from: d  reason: collision with root package name */
        public static final int f8840d = 3;

        /* renamed from: e  reason: collision with root package name */
        public static final int f8841e = 4;

        /* renamed from: f  reason: collision with root package name */
        public static final int f8842f = 5;

        /* renamed from: g  reason: collision with root package name */
        public static final int f8843g = 6;

        /* renamed from: h  reason: collision with root package name */
        public static final int f8844h = 7;

        /* renamed from: i  reason: collision with root package name */
        public static final int f8845i = 8;
    }

    private UserConfig(Builder builder) {
        this.appContext = builder.mAppContext;
        this.appId = builder.mAppId;
        this.region = builder.mRegion;
        this.isSandbox = builder.mIsSandbox;
        this.usePacketChannel = builder.mUsePacketChannel;
        this.appChannel = builder.mAppChannel;
        this.productFlavor = builder.mProductFlavor;
        this.f8835a = builder.f8836a;
    }
}
