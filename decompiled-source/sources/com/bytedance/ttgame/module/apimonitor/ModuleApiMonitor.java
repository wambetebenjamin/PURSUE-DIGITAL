package com.bytedance.ttgame.module.apimonitor;
/* loaded from: classes4.dex */
public class ModuleApiMonitor {
    private final ApiApmHandler apmHandler = new ApiApmHandler();
    private final InternalApiHandler internalApiHandler = new InternalApiHandler();

    /* JADX WARN: Multi-variable type inference failed */
    public void onApiEnter(String str, String str2, String str3, String str4, String[] strArr, String str5) {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3 = null;
        try {
            cls = Class.forName(str2);
            try {
                cls3 = Class.forName(str3);
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                cls2 = cls;
                Class<?> cls4 = cls3;
                if (cls2 != null) {
                    return;
                }
                return;
            }
        } catch (Throwable th2) {
            th = th2;
            cls = null;
        }
        cls2 = cls;
        Class<?> cls42 = cls3;
        if (cls2 != null || cls42 == null) {
            return;
        }
        this.apmHandler.onApiEnter(str, cls2, cls42, str4, strArr, str5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onApiExit(String str, String str2, String str3, String str4, String[] strArr, String str5) {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3 = null;
        try {
            cls = Class.forName(str2);
            try {
                cls3 = Class.forName(str3);
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                cls2 = cls;
                Class<?> cls4 = cls3;
                if (cls2 != null) {
                    return;
                }
                return;
            }
        } catch (Throwable th2) {
            th = th2;
            cls = null;
        }
        cls2 = cls;
        Class<?> cls42 = cls3;
        if (cls2 != null || cls42 == null) {
            return;
        }
        this.apmHandler.onApiExit(str, cls2, cls42, str4, strArr, str5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onProxyApiEnter(String str, String str2, String str3, String str4, String[] strArr, String str5) {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3 = null;
        try {
            cls = Class.forName(str2);
            try {
                cls3 = Class.forName(str3);
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                cls2 = cls;
                Class<?> cls4 = cls3;
                if (cls2 != null) {
                    return;
                }
                return;
            }
        } catch (Throwable th2) {
            th = th2;
            cls = null;
        }
        cls2 = cls;
        Class<?> cls42 = cls3;
        if (cls2 != null || cls42 == null) {
            return;
        }
        this.internalApiHandler.onProxyApiEnter(str, cls2, cls42, str4, strArr, str5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onProxyApiExit(String str, String str2, String str3, String str4, String[] strArr, String str5) {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3 = null;
        try {
            cls = Class.forName(str2);
            try {
                cls3 = Class.forName(str3);
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                cls2 = cls;
                Class<?> cls4 = cls3;
                if (cls2 != null) {
                    return;
                }
                return;
            }
        } catch (Throwable th2) {
            th = th2;
            cls = null;
        }
        cls2 = cls;
        Class<?> cls42 = cls3;
        if (cls2 != null || cls42 == null) {
            return;
        }
        this.internalApiHandler.onProxyApiExit(str, cls2, cls42, str4, strArr, str5);
    }
}
