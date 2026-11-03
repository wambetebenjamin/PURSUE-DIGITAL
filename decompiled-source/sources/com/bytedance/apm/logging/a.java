package com.bytedance.apm.logging;
/* compiled from: ApmAlogHelper.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f1212a = true;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f1213b;

    /* renamed from: c  reason: collision with root package name */
    private static c f1214c;

    /* renamed from: d  reason: collision with root package name */
    private static c f1215d;

    /* renamed from: e  reason: collision with root package name */
    private static volatile com.bytedance.apm6.foundation.b f1216e;

    public static void setEnable(boolean z) {
        f1212a = z;
    }

    public static void setFeedbackALogEnabled(boolean z) {
        f1213b = z;
    }

    public static boolean isFeedbackALogEnabled() {
        return f1213b;
    }

    public static void setApmLogger(c cVar) {
        f1214c = cVar;
        if (cVar != null) {
            f1212a = true;
        }
    }

    public static void setUnSampleListener(com.bytedance.apm6.foundation.b bVar) {
        f1216e = bVar;
    }

    public static c getApmLogger() {
        return f1214c;
    }

    public static void d(String str, String str2) {
        c cVar;
        if (!f1212a || (cVar = f1214c) == null) {
            return;
        }
        cVar.d(str, str2);
    }

    public static void e(String str, String str2) {
        c cVar;
        if (!f1212a || (cVar = f1214c) == null) {
            return;
        }
        cVar.e(str, str2);
    }

    public static void i(String str, String str2) {
        c cVar;
        if (!f1212a || (cVar = f1214c) == null) {
            return;
        }
        cVar.i(str, str2);
    }

    public static void feedbackI(String str, String str2) {
        c cVar;
        if (!f1213b || (cVar = f1214c) == null) {
            return;
        }
        cVar.i(str, str2);
    }

    public static void onUnSampleLog(String str, String str2) {
        if (f1216e != null) {
            f1216e.onLog(str, str2);
        }
    }

    public static boolean isUnSampleEnable() {
        return f1216e != null;
    }

    public static void setApmInnerAlog(c cVar) {
        f1215d = cVar;
    }

    public static c getInnerLogger() {
        return f1215d;
    }
}
