package com.bytedance.article.common.monitor.stack;

import android.text.TextUtils;
import com.bytedance.apm.util.ag;
import java.util.Collection;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: ExceptionMonitor.java */
@Deprecated
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f3633a;

    public static void setDebug(boolean z) {
        f3633a = z;
    }

    public static boolean ensureTrue(boolean z) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureTrue(z);
        }
        return a(z);
    }

    public static boolean ensureFalse(boolean z) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureFalse(z);
        }
        return b(z);
    }

    public static boolean ensureTrue(boolean z, String str) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureTrue(z, str);
        }
        return a(z, str);
    }

    public static boolean ensureTrue(boolean z, String str, Map<String, String> map) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureTrue(z, str, map);
        }
        return a(z, str, map);
    }

    public static boolean ensureFalse(boolean z, String str) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureFalse(z, str);
        }
        return b(z, str);
    }

    public static boolean ensureFalse(boolean z, String str, Map<String, String> map) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureFalse(z, str, map);
        }
        return b(z, str, map);
    }

    public static void ensureNotReachHere() {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            com.bytedance.services.apm.api.b.ensureNotReachHere();
        } else {
            a();
        }
    }

    public static void ensureNotReachHere(String str) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            com.bytedance.services.apm.api.b.ensureNotReachHere(str);
        } else {
            a(str);
        }
    }

    public static void ensureNotReachHere(Throwable th) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            com.bytedance.services.apm.api.b.ensureNotReachHere(th);
        } else {
            a(th);
        }
    }

    public static void ensureNotReachHere(String str, Map<String, String> map) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            com.bytedance.services.apm.api.b.ensureNotReachHere(str, map);
        } else {
            a(str, map);
        }
    }

    public static void reportLogEException(int i2, Throwable th, String str, boolean z) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            com.bytedance.services.apm.api.b.reportLogEException(i2, th, str, z);
        } else {
            a(i2, th, str, z);
        }
    }

    public static void ensureNotReachHere(Throwable th, String str) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            com.bytedance.services.apm.api.b.ensureNotReachHere(th, str);
        } else {
            a(th, str);
        }
    }

    public static void ensureNotReachHere(Throwable th, String str, Map<String, String> map) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            com.bytedance.services.apm.api.b.ensureNotReachHere(th, str, map);
        } else {
            a(th, str, map);
        }
    }

    public static boolean ensureNotEmpty(Collection collection) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureNotEmpty(collection);
        }
        return a(collection);
    }

    public static boolean ensureNotNull(Object obj) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureNotNull(obj);
        }
        return a(obj);
    }

    public static boolean ensureNotNull(Object obj, String str) {
        if (com.bytedance.services.apm.api.b.getEnsureImpl() != null) {
            return com.bytedance.services.apm.api.b.ensureNotNull(obj, str);
        }
        return a(obj, str);
    }

    private static boolean a(boolean z) {
        if (!z) {
            a(3, Thread.currentThread().getStackTrace(), null, true, null);
        }
        return z;
    }

    private static boolean b(boolean z) {
        if (z) {
            a(3, Thread.currentThread().getStackTrace(), null, true, null);
        }
        return z;
    }

    private static boolean a(boolean z, String str) {
        if (!z) {
            a(3, Thread.currentThread().getStackTrace(), str, true, null);
        }
        return z;
    }

    private static boolean a(boolean z, String str, Map<String, String> map) {
        if (!z) {
            b(str, map);
        }
        return z;
    }

    private static boolean b(boolean z, String str) {
        if (z) {
            a(3, Thread.currentThread().getStackTrace(), str, true, null);
        }
        return z;
    }

    private static boolean b(boolean z, String str, Map<String, String> map) {
        if (z) {
            b(str, map);
        }
        return z;
    }

    private static void a() {
        a(3, Thread.currentThread().getStackTrace(), null, true, null);
    }

    private static void a(String str) {
        a(3, Thread.currentThread().getStackTrace(), str, true, null);
    }

    private static void a(Throwable th) {
        if (b(th)) {
            a(3, Thread.currentThread().getStackTrace(), th, null, true, true, null);
        }
    }

    private static void a(int i2, Throwable th, String str, boolean z) {
        if (b(th)) {
            a(i2 + 2, Thread.currentThread().getStackTrace(), th, str, z, false, null);
        }
    }

    private static boolean b(Throwable th) {
        if (c.getInstance().getExceptionFilterSwitch()) {
            return !d.filterException(th);
        }
        return true;
    }

    private static void a(Throwable th, String str) {
        if (b(th)) {
            a(3, Thread.currentThread().getStackTrace(), th, str, true, true, null);
        }
    }

    private static boolean a(Collection collection) {
        boolean z = (collection == null || collection.size() == 0) ? false : true;
        if (!z) {
            a(3, Thread.currentThread().getStackTrace(), null, true, null);
        }
        return z;
    }

    private static boolean a(Object obj) {
        boolean z = obj != null;
        if (!z) {
            a(3, Thread.currentThread().getStackTrace(), null, true, null);
        }
        return z;
    }

    private static boolean a(Object obj, String str) {
        boolean z = obj != null;
        if (!z) {
            a(3, Thread.currentThread().getStackTrace(), str, true, null);
        }
        return z;
    }

    private static void a(Throwable th, String str, Map<String, String> map) {
        if (b(th)) {
            a(3, Thread.currentThread().getStackTrace(), th, str, true, false, map);
        }
    }

    private static void a(String str, Map<String, String> map) {
        b(str, map);
    }

    private static void a(int i2, StackTraceElement[] stackTraceElementArr, Throwable th, String str, boolean z, boolean z2, Map<String, String> map) {
        if (th == null) {
            return;
        }
        try {
            StackTraceElement stackTraceElement = stackTraceElementArr[i2];
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            String throwableStack = ag.getThrowableStack(th);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_type", "exception");
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put(com.bytedance.crash.event.f.f6711c, className);
            jSONObject.put("method", methodName);
            jSONObject.put(com.bytedance.crash.event.f.f6713e, lineNumber);
            jSONObject.put("stack", throwableStack);
            jSONObject.put(com.bytedance.apm.perf.traffic.a.KEY_EXCEPTION_TYPE, 0);
            if (z2) {
                jSONObject.put("is_core", 1);
            } else {
                jSONObject.put("is_core", 0);
            }
            if (!TextUtils.isEmpty(str)) {
                if (str.length() > 1024) {
                    jSONObject.put("message", str.substring(0, 1024));
                } else {
                    jSONObject.put("message", str);
                }
            }
            a(map, jSONObject);
            c.getInstance().enqueue("core_exception_monitor", jSONObject.toString(), str, z);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private static void b(String str, Map<String, String> map) {
        a(3, Thread.currentThread().getStackTrace(), str, false, map);
    }

    private static void a(int i2, StackTraceElement[] stackTraceElementArr, String str, boolean z, Map<String, String> map) {
        StackTraceElement stackTraceElement;
        if (stackTraceElementArr != null) {
            try {
                if (stackTraceElementArr.length > i2 + 1 && (stackTraceElement = stackTraceElementArr[i2]) != null) {
                    String className = stackTraceElement.getClassName();
                    String methodName = stackTraceElement.getMethodName();
                    int lineNumber = stackTraceElement.getLineNumber();
                    String stackTraces = ag.getStackTraces(stackTraceElementArr);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("event_type", "exception");
                    jSONObject.put("timestamp", System.currentTimeMillis());
                    jSONObject.put(com.bytedance.crash.event.f.f6711c, className);
                    jSONObject.put("method", methodName);
                    jSONObject.put(com.bytedance.crash.event.f.f6713e, lineNumber);
                    jSONObject.put("stack", stackTraces);
                    jSONObject.put(com.bytedance.apm.perf.traffic.a.KEY_EXCEPTION_TYPE, 1);
                    jSONObject.put("is_core", 1);
                    if (!TextUtils.isEmpty(str)) {
                        if (str.length() > 1024) {
                            jSONObject.put("message", str.substring(0, 1024));
                        } else {
                            jSONObject.put("message", str);
                        }
                    }
                    a(map, jSONObject);
                    c.getInstance().enqueue("core_exception_monitor", jSONObject.toString(), str, z);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void setUploadUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c.setUploadUrl(str);
    }

    private static void a(Map<String, String> map, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (map != null) {
                for (String str : map.keySet()) {
                    jSONObject2.put(str, map.get(str));
                }
                jSONObject.put("custom", jSONObject2);
            }
        } catch (Throwable unused) {
        }
    }
}
