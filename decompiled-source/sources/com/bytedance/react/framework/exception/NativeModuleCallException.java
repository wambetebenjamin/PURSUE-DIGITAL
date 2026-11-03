package com.bytedance.react.framework.exception;
/* loaded from: classes2.dex */
public class NativeModuleCallException extends Exception {
    int code;

    public NativeModuleCallException(Exception exc) {
        super(exc);
        this.code = -2001;
    }

    public int getCode() {
        return this.code;
    }
}
