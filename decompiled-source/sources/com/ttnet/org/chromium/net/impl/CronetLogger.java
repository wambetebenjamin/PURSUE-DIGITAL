package com.ttnet.org.chromium.net.impl;

import com.bytedance.librarian.c;
import java.time.Duration;
/* loaded from: classes9.dex */
public abstract class CronetLogger {

    /* loaded from: classes9.dex */
    public enum CronetSource {
        CRONET_SOURCE_UNSPECIFIED,
        CRONET_SOURCE_STATICALLY_LINKED,
        CRONET_SOURCE_PLAY_SERVICES,
        CRONET_SOURCE_FALLBACK
    }

    public abstract void logCronetEngineCreation(int i2, CronetEngineBuilderInfo cronetEngineBuilderInfo, CronetVersion cronetVersion, CronetSource cronetSource);

    public abstract void logCronetTrafficInfo(int i2, CronetTrafficInfo cronetTrafficInfo);

    /* loaded from: classes9.dex */
    public static class CronetEngineBuilderInfo {
        private final boolean mBrotiEnabled;
        private final String mExperimentalOptions;
        private final boolean mHttp2Enabled;
        private final int mHttpCacheMode;
        private final boolean mNetworkQualityEstimatorEnabled;
        private final boolean mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
        private final boolean mQuicEnabled;
        private final String mStoragePath;
        private final int mThreadPriority;
        private final String mUserAgent;

        public CronetEngineBuilderInfo(CronetEngineBuilderImpl cronetEngineBuilderImpl) {
            this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled = cronetEngineBuilderImpl.publicKeyPinningBypassForLocalTrustAnchorsEnabled();
            this.mUserAgent = cronetEngineBuilderImpl.getUserAgent();
            this.mStoragePath = cronetEngineBuilderImpl.storagePath();
            this.mQuicEnabled = cronetEngineBuilderImpl.quicEnabled();
            this.mHttp2Enabled = cronetEngineBuilderImpl.http2Enabled();
            this.mBrotiEnabled = cronetEngineBuilderImpl.brotliEnabled();
            this.mHttpCacheMode = cronetEngineBuilderImpl.publicBuilderHttpCacheMode();
            this.mExperimentalOptions = cronetEngineBuilderImpl.experimentalOptions();
            this.mNetworkQualityEstimatorEnabled = cronetEngineBuilderImpl.networkQualityEstimatorEnabled();
            this.mThreadPriority = cronetEngineBuilderImpl.threadPriority(10);
        }

        public boolean isPublicKeyPinningBypassForLocalTrustAnchorsEnabled() {
            return this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
        }

        public String getUserAgent() {
            return this.mUserAgent;
        }

        public String getStoragePath() {
            return this.mStoragePath;
        }

        public boolean isQuicEnabled() {
            return this.mQuicEnabled;
        }

        public boolean isHttp2Enabled() {
            return this.mHttp2Enabled;
        }

        public boolean isBrotliEnabled() {
            return this.mBrotiEnabled;
        }

        public int getHttpCacheMode() {
            return this.mHttpCacheMode;
        }

        public String getExperimentalOptions() {
            return this.mExperimentalOptions;
        }

        public boolean isNetworkQualityEstimatorEnabled() {
            return this.mNetworkQualityEstimatorEnabled;
        }

        public int getThreadPriority() {
            return this.mThreadPriority;
        }
    }

    /* loaded from: classes9.dex */
    public static class CronetTrafficInfo {
        private final boolean mDidConnectionMigrationSucceed;
        private final Duration mHeadersLatency;
        private final String mNegotiatedProtocol;
        private final long mRequestBodySizeInBytes;
        private final long mRequestHeaderSizeInBytes;
        private final long mResponseBodySizeInBytes;
        private final long mResponseHeaderSizeInBytes;
        private final int mResponseStatusCode;
        private final Duration mTotalLatency;
        private final boolean mWasConnectionMigrationAttempted;

        public CronetTrafficInfo(long j2, long j3, long j4, long j5, int i2, Duration duration, Duration duration2, String str, boolean z, boolean z2) {
            this.mRequestHeaderSizeInBytes = j2;
            this.mRequestBodySizeInBytes = j3;
            this.mResponseHeaderSizeInBytes = j4;
            this.mResponseBodySizeInBytes = j5;
            this.mResponseStatusCode = i2;
            this.mHeadersLatency = duration;
            this.mTotalLatency = duration2;
            this.mNegotiatedProtocol = str;
            this.mWasConnectionMigrationAttempted = z;
            this.mDidConnectionMigrationSucceed = z2;
        }

        public long getRequestHeaderSizeInBytes() {
            return this.mRequestHeaderSizeInBytes;
        }

        public long getRequestBodySizeInBytes() {
            return this.mRequestBodySizeInBytes;
        }

        public long getResponseHeaderSizeInBytes() {
            return this.mResponseHeaderSizeInBytes;
        }

        public long getResponseBodySizeInBytes() {
            return this.mResponseBodySizeInBytes;
        }

        public int getResponseStatusCode() {
            return this.mResponseStatusCode;
        }

        public Duration getHeadersLatency() {
            return this.mHeadersLatency;
        }

        public Duration getTotalLatency() {
            return this.mTotalLatency;
        }

        public String getNegotiatedProtocol() {
            return this.mNegotiatedProtocol;
        }

        public boolean wasConnectionMigrationAttempted() {
            return this.mWasConnectionMigrationAttempted;
        }

        public boolean didConnectionMigrationSucceed() {
            return this.mDidConnectionMigrationSucceed;
        }
    }

    /* loaded from: classes9.dex */
    public static class CronetVersion {
        private final int mBuildVersion;
        private final int mMajorVersion;
        private final int mMinorVersion;
        private final int mPatchVersion;

        public CronetVersion(String str) {
            String[] split = str.split("\\.");
            this.mMajorVersion = Integer.parseInt(split[0]);
            this.mMinorVersion = Integer.parseInt(split[1]);
            this.mBuildVersion = Integer.parseInt(split[2]);
            this.mPatchVersion = Integer.parseInt(split[3]);
        }

        public int getMajorVersion() {
            return this.mMajorVersion;
        }

        public int getMinorVersion() {
            return this.mMinorVersion;
        }

        public int getBuildVersion() {
            return this.mBuildVersion;
        }

        public int getPatchVersion() {
            return this.mPatchVersion;
        }

        public String toString() {
            return "" + this.mMajorVersion + c.a.DOT + this.mMinorVersion + c.a.DOT + this.mBuildVersion + c.a.DOT + this.mPatchVersion;
        }
    }
}
