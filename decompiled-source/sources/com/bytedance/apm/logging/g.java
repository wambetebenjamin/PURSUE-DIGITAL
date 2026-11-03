package com.bytedance.apm.logging;

import com.bytedance.apm.logging.f;
/* compiled from: MethodLogger.java */
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static f.a f1219a = f.sLog;

    private g() {
    }

    public static void i(String str, String str2, Object... objArr) {
        f.a aVar = f1219a;
        if (aVar != null) {
            aVar.i(str, str2, objArr);
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        f.a aVar = f1219a;
        if (aVar != null) {
            aVar.v(str, str2, objArr);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        f.a aVar = f1219a;
        if (aVar != null) {
            aVar.w(str, str2, objArr);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        f.a aVar = f1219a;
        if (aVar != null) {
            aVar.d(str, str2, objArr);
        }
    }

    public static void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        f.a aVar = f1219a;
        if (aVar != null) {
            aVar.printErrStackTrace(str, th, str2, objArr);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        f.a aVar = f1219a;
        if (aVar != null) {
            aVar.e(str, str2, objArr);
        }
    }
}
