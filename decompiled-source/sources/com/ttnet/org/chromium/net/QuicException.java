package com.ttnet.org.chromium.net;
/* loaded from: classes9.dex */
public abstract class QuicException extends NetworkException {
    public abstract int getQuicDetailedErrorCode();

    /* JADX INFO: Access modifiers changed from: protected */
    public QuicException(String str, Throwable th) {
        super(str, th);
    }
}
