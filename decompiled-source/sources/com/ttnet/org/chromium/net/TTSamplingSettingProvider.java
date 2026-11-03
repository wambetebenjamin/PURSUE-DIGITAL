package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public abstract class TTSamplingSettingProvider {

    /* loaded from: classes9.dex */
    public static class TTSlaSamplingSetting {
        public TTApiLogSamplingRule mApiLogSamplingRule;
        public TTEventLogSamplingRule mEventLogSamplingRule;
        public int mHostAid;
        public int mSdkAid;

        public int getHostAid() {
            return this.mHostAid;
        }

        public int getSdkAid() {
            return this.mSdkAid;
        }

        public TTApiLogSamplingRule getApiLogSamplingRule() {
            return this.mApiLogSamplingRule;
        }

        public TTEventLogSamplingRule getEventLogSamplingRule() {
            return this.mEventLogSamplingRule;
        }
    }

    /* loaded from: classes9.dex */
    public static class TTApiLogSamplingRule {
        public String[] mApiAllowList;
        public boolean mEnableApiAllUpload;
        public boolean mEnableBaseApiAll;
        public String[] mUrlRegexBlackList;

        public boolean isEnableBaseApiAll() {
            return this.mEnableBaseApiAll;
        }

        public boolean isEnableApiAllUpload() {
            return this.mEnableApiAllUpload;
        }

        public String[] getUrlRegexBlackList() {
            return this.mUrlRegexBlackList;
        }

        public String[] getApiAllowList() {
            return this.mApiAllowList;
        }
    }

    /* loaded from: classes9.dex */
    public static class TTEventLogSamplingRule {
        public String[] mEventLogTypeWhiteList;

        public String[] getEventLogTypeWhiteList() {
            return this.mEventLogTypeWhiteList;
        }
    }
}
