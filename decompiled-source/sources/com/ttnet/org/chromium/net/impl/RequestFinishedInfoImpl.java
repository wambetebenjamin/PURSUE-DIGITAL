package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.CronetException;
import com.ttnet.org.chromium.net.RequestFinishedInfo;
import com.ttnet.org.chromium.net.UrlResponseInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Collections;
/* loaded from: classes9.dex */
public class RequestFinishedInfoImpl extends RequestFinishedInfo {
    private final Collection<Object> mAnnotations;
    private final CronetException mException;
    private final int mFinishedReason;
    private final RequestFinishedInfo.Metrics mMetrics;
    private final UrlResponseInfo mResponseInfo;
    private final String mUrl;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface FinishedReason {
    }

    public RequestFinishedInfoImpl(String str, Collection<Object> collection, RequestFinishedInfo.Metrics metrics, int i2, UrlResponseInfo urlResponseInfo, CronetException cronetException) {
        this.mUrl = str;
        this.mAnnotations = collection;
        this.mMetrics = metrics;
        this.mFinishedReason = i2;
        this.mResponseInfo = urlResponseInfo;
        this.mException = cronetException;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo
    public String getUrl() {
        return this.mUrl;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo
    public Collection<Object> getAnnotations() {
        Collection<Object> collection = this.mAnnotations;
        return collection == null ? Collections.emptyList() : collection;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo
    public RequestFinishedInfo.Metrics getMetrics() {
        return this.mMetrics;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo
    public int getFinishedReason() {
        return this.mFinishedReason;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo
    public UrlResponseInfo getResponseInfo() {
        return this.mResponseInfo;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo
    public CronetException getException() {
        return this.mException;
    }
}
