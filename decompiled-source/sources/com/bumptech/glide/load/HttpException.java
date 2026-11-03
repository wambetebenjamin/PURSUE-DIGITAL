package com.bumptech.glide.load;

import java.io.IOException;
/* loaded from: classes.dex */
public final class HttpException extends IOException {
    public static final int UNKNOWN = -1;
    private static final long serialVersionUID = 1;
    private final int statusCode;

    public HttpException(int i2) {
        this("Http request failed with status code: " + i2, i2);
    }

    public HttpException(String str) {
        this(str, -1);
    }

    public HttpException(String str, int i2) {
        this(str, i2, null);
    }

    public HttpException(String str, int i2, Throwable th) {
        super(str, th);
        this.statusCode = i2;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
