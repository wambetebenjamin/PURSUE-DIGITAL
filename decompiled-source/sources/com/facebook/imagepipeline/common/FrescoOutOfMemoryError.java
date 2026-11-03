package com.facebook.imagepipeline.common;
/* loaded from: classes8.dex */
public class FrescoOutOfMemoryError extends OutOfMemoryError {
    public Object context;
    public OutOfMemoryError outOfMemoryError;

    public FrescoOutOfMemoryError(OutOfMemoryError outOfMemoryError) {
        this.outOfMemoryError = outOfMemoryError;
    }

    @Override // java.lang.Throwable
    public StackTraceElement[] getStackTrace() {
        return this.outOfMemoryError.getStackTrace();
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.outOfMemoryError.getLocalizedMessage();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.outOfMemoryError.getMessage();
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        return this.outOfMemoryError.getCause();
    }
}
