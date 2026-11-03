package com.facebook.react.jstasks;

import com.facebook.react.bridge.WritableMap;
/* loaded from: classes8.dex */
public class HeadlessJsTaskConfig {
    private final boolean mAllowedInForeground;
    private final WritableMap mData;
    private final HeadlessJsTaskRetryPolicy mRetryPolicy;
    private final String mTaskKey;
    private final long mTimeout;

    public HeadlessJsTaskConfig(String str, WritableMap writableMap) {
        this(str, writableMap, 0L, false);
    }

    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j2) {
        this(str, writableMap, j2, false);
    }

    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j2, boolean z) {
        this(str, writableMap, j2, z, NoRetryPolicy.INSTANCE);
    }

    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j2, boolean z, HeadlessJsTaskRetryPolicy headlessJsTaskRetryPolicy) {
        this.mTaskKey = str;
        this.mData = writableMap;
        this.mTimeout = j2;
        this.mAllowedInForeground = z;
        this.mRetryPolicy = headlessJsTaskRetryPolicy;
    }

    public HeadlessJsTaskConfig(HeadlessJsTaskConfig headlessJsTaskConfig) {
        this.mTaskKey = headlessJsTaskConfig.mTaskKey;
        this.mData = headlessJsTaskConfig.mData.copy();
        this.mTimeout = headlessJsTaskConfig.mTimeout;
        this.mAllowedInForeground = headlessJsTaskConfig.mAllowedInForeground;
        HeadlessJsTaskRetryPolicy headlessJsTaskRetryPolicy = headlessJsTaskConfig.mRetryPolicy;
        if (headlessJsTaskRetryPolicy != null) {
            this.mRetryPolicy = headlessJsTaskRetryPolicy.copy();
        } else {
            this.mRetryPolicy = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTaskKey() {
        return this.mTaskKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WritableMap getData() {
        return this.mData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getTimeout() {
        return this.mTimeout;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAllowedInForeground() {
        return this.mAllowedInForeground;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HeadlessJsTaskRetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }
}
