package com.bytedance.android.alog;

import java.io.File;
import java.util.HashMap;
/* compiled from: Log.java */
@Deprecated
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static volatile Alog f188a;

    public static void setInstance(Alog alog) {
        f188a = alog;
        Alog.nativeSetDefaultInstance(alog == null ? 0L : alog.getNativeRef());
    }

    public static Alog getInstance() {
        return f188a;
    }

    public static void close() {
        Alog.nativeSetDefaultInstance(0L);
        f188a.close();
        f188a = null;
    }

    public static void asyncFlush() {
        if (f188a != null) {
            f188a.asyncFlush();
        }
    }

    public static void syncFlush() {
        if (f188a != null) {
            f188a.syncFlush();
        }
    }

    public static void timedSyncFlush(int i2) {
        if (f188a != null) {
            f188a.timedSyncFlush(i2);
        }
    }

    public static void setLevel(int i2) {
        if (f188a != null) {
            f188a.setLevel(i2);
        }
    }

    public static void setSyslog(boolean z) {
        if (f188a != null) {
            f188a.setSyslog(z);
        }
    }

    public static File[] getLogs(long j2, long j3) {
        return f188a != null ? f188a.getLogs(j2, j3) : new File[0];
    }

    public static File[] getLogs(String str, String str2, long j2, long j3) {
        return f188a != null ? f188a.getLogs(str, str2, j2, j3) : new File[0];
    }

    public static HashMap<String, String> getLastFetchErrorInfo() {
        if (f188a != null) {
            return f188a.getLastFetchErrorInfo();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(com.bytedance.pipo.payment.common.lib.c.de, "Log.instance does not exist");
        return hashMap;
    }

    public static String getStatus() {
        return f188a != null ? f188a.getStatus() : "default log instance is null";
    }

    public static long getLegacyWriteFuncAddr() {
        if (f188a != null) {
            return f188a.getLegacyWriteFuncAddr();
        }
        return 0L;
    }

    public static long getLegacyFlushFuncAddr() {
        if (f188a != null) {
            return f188a.getLegacyFlushFuncAddr();
        }
        return 0L;
    }

    public static long getLegacyGetLogFileDirFuncAddr() {
        if (f188a != null) {
            return f188a.getLegacyGetLogFileDirFuncAddr();
        }
        return 0L;
    }

    public static long getNativeWriteFuncAddr() {
        if (f188a != null) {
            return f188a.getNativeWriteFuncAddr();
        }
        return 0L;
    }

    public static long a() {
        if (f188a != null) {
            return f188a.h();
        }
        return 0L;
    }

    public static long b() {
        if (f188a != null) {
            return f188a.i();
        }
        return 0L;
    }

    @Deprecated
    public static void v(String str, String str2) {
        com.ss.android.agilelogger.a.v(str, str2);
    }

    @Deprecated
    public static void d(String str, String str2) {
        com.ss.android.agilelogger.a.d(str, str2);
    }

    @Deprecated
    public static void i(String str, String str2) {
        com.ss.android.agilelogger.a.i(str, str2);
    }

    @Deprecated
    public static void w(String str, String str2) {
        com.ss.android.agilelogger.a.w(str, str2);
    }

    @Deprecated
    public static void e(String str, String str2) {
        com.ss.android.agilelogger.a.e(str, str2);
    }

    @Deprecated
    public static void f(String str, String str2) {
        com.ss.android.agilelogger.a.e(str, str2);
    }

    @Deprecated
    public static void write(int i2, String str, String str2) {
        if (f188a != null) {
            f188a.write(i2, str, str2);
        }
    }

    @Deprecated
    public static void writeAsyncMsg(int i2, String str, String str2, long j2, long j3) {
        if (f188a != null) {
            f188a.writeAsyncMsg(i2, str, str2, j2, j3);
        }
    }

    @Deprecated
    public static void a(String str, String str2) {
        write(0, str, str2);
    }

    @Deprecated
    public static void b(String str, String str2) {
        write(1, str, str2);
    }

    @Deprecated
    public static void c(String str, String str2) {
        write(2, str, str2);
    }

    @Deprecated
    public static void g(String str, String str2) {
        write(3, str, str2);
    }

    @Deprecated
    public static void h(String str, String str2) {
        write(4, str, str2);
    }

    @Deprecated
    public static void j(String str, String str2) {
        write(5, str, str2);
    }

    @Deprecated
    public static void a(int i2, String str, String str2) {
        if (f188a != null) {
            f188a.write(i2, str, str2);
        }
    }

    @Deprecated
    public static void a(int i2, String str, String str2, long j2, long j3) {
        if (f188a != null) {
            f188a.writeAsyncMsg(i2, str, str2, j2, j3);
        }
    }
}
