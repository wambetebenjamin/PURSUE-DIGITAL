package com.bytedance.ttgame.main.internal.net;

import java.util.Collections;
import java.util.Map;
/* loaded from: classes3.dex */
public class ApiResponse<T> {
    public final T body;
    public final int code;
    public final String errorMessage;
    public final Map<String, String> links;
    public String logId;
    public final String requestUrl;
    public final Throwable throwable;

    public ApiResponse(Throwable th, String str) {
        this.code = 500;
        this.body = null;
        this.errorMessage = th.getMessage();
        this.links = Collections.emptyMap();
        this.throwable = th;
        this.requestUrl = str;
    }

    public ApiResponse(int i2, T t, String str, Map<String, String> map, String str2, String str3) {
        this.code = i2;
        this.body = t;
        this.errorMessage = str;
        this.links = map;
        this.logId = str2;
        this.throwable = null;
        this.requestUrl = str3;
    }

    public boolean isSuccessful() {
        int i2 = this.code;
        return i2 >= 200 && i2 < 300;
    }
}
