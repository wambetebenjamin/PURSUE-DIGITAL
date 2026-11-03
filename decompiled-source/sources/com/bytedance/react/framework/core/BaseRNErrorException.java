package com.bytedance.react.framework.core;
/* loaded from: classes2.dex */
public abstract class BaseRNErrorException extends Exception {
    public String code;

    public BaseRNErrorException(String str, String str2) {
        super(str2);
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }
}
