package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.QuicException;
/* loaded from: classes9.dex */
public class QuicExceptionImpl extends QuicException {
    private final NetworkExceptionImpl mNetworkException;
    private final int mQuicDetailedErrorCode;

    public QuicExceptionImpl(String str, int i2, int i3, int i4) {
        super(str, null);
        this.mNetworkException = new NetworkExceptionImpl(str, i2, i3);
        this.mQuicDetailedErrorCode = i4;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb = new StringBuilder(this.mNetworkException.getMessage());
        sb.append(", QuicDetailedErrorCode=").append(this.mQuicDetailedErrorCode);
        return sb.toString();
    }

    @Override // com.ttnet.org.chromium.net.NetworkException
    public int getErrorCode() {
        return this.mNetworkException.getErrorCode();
    }

    @Override // com.ttnet.org.chromium.net.NetworkException
    public int getCronetInternalErrorCode() {
        return this.mNetworkException.getCronetInternalErrorCode();
    }

    @Override // com.ttnet.org.chromium.net.NetworkException
    public boolean immediatelyRetryable() {
        return this.mNetworkException.immediatelyRetryable();
    }

    @Override // com.ttnet.org.chromium.net.QuicException
    public int getQuicDetailedErrorCode() {
        return this.mQuicDetailedErrorCode;
    }
}
