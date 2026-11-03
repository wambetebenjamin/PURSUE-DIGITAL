package com.ttnet.org.chromium.base.library_loader;
/* loaded from: classes9.dex */
public class ProcessInitException extends RuntimeException {
    public ProcessInitException(int i2) {
        super("errorCode=" + i2);
    }

    public ProcessInitException(int i2, Throwable th) {
        super("errorCode=" + i2, th);
    }
}
