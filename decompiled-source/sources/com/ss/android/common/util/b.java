package com.ss.android.common.util;

import com.bytedance.applog.log.h;
import com.bytedance.applog.log.o;
/* compiled from: TLog.java */
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final h f18095a = o.a();

    /* renamed from: b  reason: collision with root package name */
    private static volatile a f18096b;

    public static void setLogger(a aVar) {
        f18096b = aVar;
    }

    public static void v(String str) {
        v(str, null);
    }

    public static void d(String str) {
        d(str, null);
    }

    public static void i(String str) {
        i(str, null);
    }

    public static void w(String str) {
        w(str, null);
    }

    public static void e(String str) {
        e(str, null);
    }

    public static void v(String str, Throwable th) {
        a aVar = f18096b;
        if (aVar != null) {
            aVar.v(str, th);
        }
        f18095a.b(str, new Object[0]);
    }

    public static void d(String str, Throwable th) {
        a aVar = f18096b;
        if (aVar != null) {
            aVar.d(str, th);
        }
        f18095a.d(str, new Object[0]);
    }

    public static void i(String str, Throwable th) {
        a aVar = f18096b;
        if (aVar != null) {
            aVar.i(str, th);
        }
        f18095a.c(str, new Object[0]);
    }

    public static void w(String str, Throwable th) {
        a aVar = f18096b;
        if (aVar != null) {
            aVar.w(str, th);
        }
        f18095a.e(str, new Object[0]);
    }

    public static void e(String str, Throwable th) {
        a aVar = f18096b;
        if (aVar != null) {
            aVar.e(str, th);
        }
        f18095a.a(str, th, new Object[0]);
    }
}
