package com.twitter.sdk.android.core;
/* loaded from: classes10.dex */
public interface Logger {
    void d(String str, String str2);

    void d(String str, String str2, Throwable th);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    int getLogLevel();

    void i(String str, String str2);

    void i(String str, String str2, Throwable th);

    boolean isLoggable(String str, int i2);

    void log(int i2, String str, String str2);

    void log(int i2, String str, String str2, boolean z);

    void setLogLevel(int i2);

    void v(String str, String str2);

    void v(String str, String str2, Throwable th);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);
}
