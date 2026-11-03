package com.facebook.jni;
/* loaded from: classes8.dex */
public class CppSystemErrorException extends CppException {
    int errorCode;

    public CppSystemErrorException(String str, int i2) {
        super(str);
        this.errorCode = i2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
