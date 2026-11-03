package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.TTNetDiagnosisRequest;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public class TTNetDiagnosisRequestBuilderImpl extends TTNetDiagnosisRequest.Builder {
    private static final String TAG = "TTNetDiagnosisRequestBuilderImpl";
    private final TTNetDiagnosisRequest.Callback mCallback;
    private final CronetEngineBase mCronetEngine;
    private final Executor mExecutor;
    private int mMultiNetAction;
    private int mNetDetectType;
    private int mReqType;
    private List<String> mTargets;
    private int mTimeoutMs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TTNetDiagnosisRequestBuilderImpl(TTNetDiagnosisRequest.Callback callback, Executor executor, CronetEngineBase cronetEngineBase) {
        this.mCronetEngine = cronetEngineBase;
        this.mCallback = callback;
        this.mExecutor = executor;
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest.Builder
    public TTNetDiagnosisRequest.Builder setRequestType(int i2) {
        this.mReqType = i2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest.Builder
    public TTNetDiagnosisRequest.Builder setTargets(List<String> list) {
        this.mTargets = list;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest.Builder
    public TTNetDiagnosisRequest.Builder setNetDetectType(int i2) {
        this.mNetDetectType = i2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest.Builder
    public TTNetDiagnosisRequest.Builder setMultiNetAction(int i2) {
        this.mMultiNetAction = i2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest.Builder
    public TTNetDiagnosisRequest.Builder setTimeout(int i2) {
        this.mTimeoutMs = i2;
        return this;
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest.Builder
    public TTNetDiagnosisRequest build() {
        return this.mCronetEngine.createNetDiagnosisRequest(this.mCallback, this.mExecutor, this.mReqType, this.mTargets, this.mNetDetectType, this.mMultiNetAction, this.mTimeoutMs);
    }
}
