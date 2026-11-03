package com.bytedance.push.log;

import com.bytedance.push.e;
/* compiled from: ILogger.java */
/* loaded from: classes2.dex */
public interface a extends e {
    void a(String str, String str2, Throwable th);

    void d(String str);

    void d(String str, String str2);

    boolean debug();

    void e(String str);

    void e(String str, String str2);

    void i(String str);

    void i(String str, String str2);

    void i(String str, String str2, Throwable th);

    void v(String str, String str2);

    void w(String str, String str2);
}
