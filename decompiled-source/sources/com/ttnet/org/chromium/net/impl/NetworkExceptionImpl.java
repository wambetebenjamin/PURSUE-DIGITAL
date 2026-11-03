package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.NetworkException;
/* loaded from: classes9.dex */
public class NetworkExceptionImpl extends NetworkException {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final int mCronetInternalErrorCode;
    protected final int mErrorCode;

    public NetworkExceptionImpl(String str, int i2, int i3) {
        super(str, null);
        this.mErrorCode = i2;
        this.mCronetInternalErrorCode = i3;
    }

    @Override // com.ttnet.org.chromium.net.NetworkException
    public int getErrorCode() {
        return this.mErrorCode;
    }

    @Override // com.ttnet.org.chromium.net.NetworkException
    public int getCronetInternalErrorCode() {
        return this.mCronetInternalErrorCode;
    }

    @Override // com.ttnet.org.chromium.net.NetworkException
    public boolean immediatelyRetryable() {
        int i2 = this.mErrorCode;
        return i2 == 3 || i2 == 4 || i2 == 5 || i2 == 6 || i2 == 8;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb = new StringBuilder(super.getMessage());
        sb.append(", ErrorCode=").append(this.mErrorCode);
        if (this.mCronetInternalErrorCode != 0) {
            sb.append(", InternalErrorCode=").append(this.mCronetInternalErrorCode);
        }
        sb.append(", Retryable=").append(immediatelyRetryable());
        return sb.toString();
    }
}
