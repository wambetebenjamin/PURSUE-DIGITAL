package com.ttnet.org.chromium.net.impl;

import android.util.Log;
import com.ttnet.org.chromium.net.TTWebsocketConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public class TTWebsocketConnectionBuilderImpl extends TTWebsocketConnection.Builder {
    private static final String TAG = "TTWebsocketConnectionBuilderImpl";
    private int mAppId;
    private String mAppKey;
    private String mAppToken;
    private int mAppVersion;
    private final TTWebsocketConnection.Callback mCallback;
    private final CronetEngineBase mCronetEngine;
    private long mDeviceId;
    private final Executor mExecutor;
    private int mFpid;
    private Map<String, String> mHeaders;
    private long mInstallId;
    private Map<String, String> mParams;
    private String mSessionId;
    private List<String> mUrlList;
    private boolean mSharedConnection = true;
    private boolean mUseFrontier = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TTWebsocketConnectionBuilderImpl(TTWebsocketConnection.Callback callback, Executor executor, CronetEngineBase cronetEngineBase) {
        Log.e(TAG, TAG);
        this.mCronetEngine = cronetEngineBase;
        this.mCallback = callback;
        this.mExecutor = executor;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setUrlList(List<String> list) {
        this.mUrlList = list;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setAppId(int i2) {
        this.mAppId = i2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setAppKey(String str) {
        this.mAppKey = str;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setDeviceId(long j2) {
        this.mDeviceId = j2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setFpid(int i2) {
        this.mFpid = i2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setInstallId(long j2) {
        this.mInstallId = j2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setSessionId(String str) {
        this.mSessionId = str;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setAppVersion(int i2) {
        this.mAppVersion = i2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setAppToken(String str) {
        this.mAppToken = str;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setCustomizedParams(Map<String, String> map) {
        this.mParams = map;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setCustomizedHeaders(Map<String, String> map) {
        this.mHeaders = map;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder setSharedConnection(boolean z) {
        this.mSharedConnection = z;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection.Builder useFrontierProtocol(boolean z) {
        this.mUseFrontier = z;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Builder
    public TTWebsocketConnection build() {
        if (this.mUseFrontier) {
            return this.mCronetEngine.createWebsocketConnection(this.mCallback, this.mExecutor, this.mUrlList, this.mAppId, this.mAppKey, this.mDeviceId, this.mFpid, this.mInstallId, this.mSessionId, this.mAppVersion, this.mAppToken, this.mParams, this.mHeaders, this.mSharedConnection);
        }
        return this.mCronetEngine.createWebsocketConnection(this.mCallback, this.mExecutor, this.mUrlList, this.mParams, this.mHeaders, this.mSharedConnection);
    }
}
