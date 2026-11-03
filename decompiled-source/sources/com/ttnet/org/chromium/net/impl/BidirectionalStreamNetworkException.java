package com.ttnet.org.chromium.net.impl;
/* loaded from: classes9.dex */
public class BidirectionalStreamNetworkException extends NetworkExceptionImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public BidirectionalStreamNetworkException(String str, int i2, int i3) {
        super(str, i2, i3);
    }

    @Override // com.ttnet.org.chromium.net.impl.NetworkExceptionImpl, com.ttnet.org.chromium.net.NetworkException
    public boolean immediatelyRetryable() {
        int i2 = this.mCronetInternalErrorCode;
        if (i2 == -358 || i2 == -352) {
            return true;
        }
        return super.immediatelyRetryable();
    }
}
