package com.bytedance.crash;

import com.bytedance.crash.crash.b;
import com.bytedance.crash.crash.c;
import com.bytedance.crash.event.l;
import com.bytedance.crash.monitor.f;
import com.bytedance.crash.monitor.h;
import com.bytedance.crash.util.y;
import java.io.File;
import java.net.BindException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;
import javax.net.ssl.SSLException;
import org.apache.http.conn.ConnectTimeoutException;
/* loaded from: classes2.dex */
public class Ensure {
    public static final String ENSURE_NOT_NULL = "EnsureNotNull";

    /* renamed from: b  reason: collision with root package name */
    public static boolean f6052b = false;

    /* renamed from: c  reason: collision with root package name */
    private static final int f6053c = 4;

    /* renamed from: d  reason: collision with root package name */
    private static f f6054d = null;

    /* renamed from: e  reason: collision with root package name */
    private static final int f6055e = 3;
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    private static final EnsureImpl f6051a = new EnsureImpl();

    /* renamed from: f  reason: collision with root package name */
    private static int f6056f = 0;

    @Deprecated
    public static boolean ensureFalse(boolean z) {
        return false;
    }

    @Deprecated
    public static boolean ensureFalse(boolean z, String str) {
        return false;
    }

    @Deprecated
    public static boolean ensureFalse(boolean z, String str, Map<String, String> map) {
        return false;
    }

    @Deprecated
    public static boolean ensureNotEmpty(Collection collection) {
        return false;
    }

    @Deprecated
    public static boolean ensureNotNull(Object obj) {
        return false;
    }

    @Deprecated
    public static boolean ensureNotNull(Object obj, String str) {
        return false;
    }

    @Deprecated
    public static boolean ensureTrue(boolean z) {
        return false;
    }

    @Deprecated
    public static boolean ensureTrue(boolean z, String str) {
        return false;
    }

    @Deprecated
    public static boolean ensureTrue(boolean z, String str, Map<String, String> map) {
        return false;
    }

    @Deprecated
    public static EnsureImpl getInstance() {
        return f6051a;
    }

    private static f a() {
        if (f6054d == null) {
            f6054d = h.a();
        }
        return f6054d;
    }

    public static void ensureNotReachHere() {
        h.b().c(4);
    }

    public static void ensureNotReachHere(String str) {
        h.b().a(str, 4);
    }

    public static void ensureNotReachHere(String str, Map<String, String> map) {
        h.b().a(str, map, 4);
    }

    public static void ensureNotReachHere(Throwable th) {
        if (a(th)) {
            return;
        }
        h.b().a(th);
    }

    public static void a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        h.b().a(str2, str3, str4, str5, map, str);
    }

    public static void a(String str, Throwable th, String str2) {
        if (a(th)) {
            return;
        }
        h.b().a(th, str2, str);
    }

    public static void ensureNativeStack(String str, String str2, String str3, String str4, Map<String, String> map) {
        h.b().a(str, str2, str3, str4, map, null);
    }

    public static void a(String str, Throwable th, String str2, String str3, Map<String, String> map, String str4, File file) {
        if (a(th) || str4 == null || file == null) {
            return;
        }
        l.a().a(th, str2, str3, true, map, str4, str, file);
    }

    public static void ensureNotReachHere(Throwable th, String str) {
        if (a(th)) {
            return;
        }
        h.b().a(th, str);
    }

    public static void ensureNotReachHere(Throwable th, String str, Map<String, String> map) {
        if (a(th)) {
            return;
        }
        h.b().a(th, str, map);
    }

    public static void a(Thread thread, Throwable th) {
        b.a().a(thread, th);
    }

    public static void ensureNotReachHereWithLogType(String str, Throwable th, String str2) {
        if (a(th)) {
            return;
        }
        h.b().a(th, str2, str);
    }

    public static void a(String str, Throwable th, String str2, Map<String, String> map) {
        if (a(th)) {
            return;
        }
        h.b().a(th, null, str2, map, Thread.currentThread().getName(), str, str, true);
    }

    public static void a(Throwable th, String str, String str2, String str3) {
        if (a(th)) {
            return;
        }
        h.b().a(th, null, str2, null, str, str3, str3, true);
    }

    public static void a(String str, String str2, String str3, String str4, String str5) {
        h.b().a(str, str2, str3, str4, null, str5);
    }

    public static void a(String str, String str2, String str3, String str4, Map<String, String> map, String str5) {
        h.b().a(str, str2, str3, str4, map, str5);
    }

    public static boolean a(Throwable th) {
        if (th == null) {
            return true;
        }
        try {
            if ((th instanceof ConnectTimeoutException) || (th instanceof SocketTimeoutException) || (th instanceof BindException) || (th instanceof ConnectException) || (th instanceof NoRouteToHostException) || (th instanceof PortUnreachableException) || (th instanceof SocketException) || (th instanceof UnknownHostException) || (th instanceof ProtocolException)) {
                return true;
            }
            return th instanceof SSLException;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return false;
        }
    }

    public static void a(boolean z) {
        f6052b = z;
    }

    public static void b(Thread thread, Throwable th) {
        if (!NpthCore.isInit()) {
            y.d("reportJavaCrash: npth is not init, dispose");
        } else if (NpthCore.isStopUpload()) {
            y.d("reportJavaCrash: stop upload, dispose");
        } else if (f6056f >= 3) {
            y.d("reportJavaCrash: > 3 times, dispose");
        } else if (!f6052b) {
            y.d("reportJavaCrash: discard because config");
        } else {
            c a2 = b.a();
            if (a2 == null) {
                return;
            }
            f6056f++;
            a2.a(thread, th);
        }
    }
}
