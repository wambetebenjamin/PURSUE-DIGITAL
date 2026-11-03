package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.BidirectionalStream;
import com.ttnet.org.chromium.net.ExperimentalBidirectionalStream;
import com.ttnet.org.chromium.net.ExperimentalCronetEngine;
import com.ttnet.org.chromium.net.ExperimentalUrlRequest;
import com.ttnet.org.chromium.net.RequestFinishedInfo;
import com.ttnet.org.chromium.net.TTNetDiagnosisRequest;
import com.ttnet.org.chromium.net.TTWebsocketConnection;
import com.ttnet.org.chromium.net.UrlRequest;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public abstract class CronetEngineBase extends ExperimentalCronetEngine {
    protected static final long DEFAULT_NETWORK_HANDLE = -1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Idempotency {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface RequestPriority {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface StreamPriority {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ExperimentalBidirectionalStream createBidirectionalStream(String str, BidirectionalStream.Callback callback, Executor executor, String str2, List<Map.Entry<String, String>> list, int i2, boolean z, Collection<Object> collection, boolean z2, int i3, boolean z3, int i4, long j2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract TTNetDiagnosisRequest createNetDiagnosisRequest(TTNetDiagnosisRequest.Callback callback, Executor executor, int i2, List<String> list, int i3, int i4, int i5);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract UrlRequestBase createRequest(String str, UrlRequest.Callback callback, Executor executor, int i2, Collection<Object> collection, boolean z, boolean z2, boolean z3, boolean z4, int i3, boolean z5, int i4, RequestFinishedInfo.Listener listener, int i5, long j2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract TTWebsocketConnection createWebsocketConnection(TTWebsocketConnection.Callback callback, Executor executor, List<String> list, int i2, String str, long j2, int i3, long j3, String str2, int i4, String str3, Map<String, String> map, Map<String, String> map2, boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract TTWebsocketConnection createWebsocketConnection(TTWebsocketConnection.Callback callback, Executor executor, List<String> list, Map<String, String> map, Map<String, String> map2, boolean z);

    @Override // com.ttnet.org.chromium.net.ExperimentalCronetEngine, com.ttnet.org.chromium.net.CronetEngine
    public ExperimentalUrlRequest.Builder newUrlRequestBuilder(String str, UrlRequest.Callback callback, Executor executor) {
        return new UrlRequestBuilderImpl(str, callback, executor, this);
    }

    @Override // com.ttnet.org.chromium.net.CronetEngine
    public TTWebsocketConnection.Builder newWebsocketConnectionBuilder(TTWebsocketConnection.Callback callback, Executor executor) {
        return new TTWebsocketConnectionBuilderImpl(callback, executor, this);
    }

    @Override // com.ttnet.org.chromium.net.CronetEngine
    public TTNetDiagnosisRequest.Builder newNetDiagnosisRequestBuilder(TTNetDiagnosisRequest.Callback callback, Executor executor) {
        return new TTNetDiagnosisRequestBuilderImpl(callback, executor, this);
    }
}
