package com.bytedance.ttgame.module.notice.api;
/* loaded from: classes6.dex */
public class NoticeFetchError {
    public int code;
    public String message;

    public NoticeFetchError(int i2, String str) {
        this.code = i2;
        this.message = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
