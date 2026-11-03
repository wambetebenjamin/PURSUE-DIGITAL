package com.bytedance.apm;

import android.util.Log;
import com.bytedance.monitor.util.thread.d;
import java.util.HashSet;
import java.util.Map;
/* compiled from: MonitorCoreExceptionManager.java */
/* loaded from: classes.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1142a = "apm_";

    /* renamed from: b  reason: collision with root package name */
    private a f1143b;

    /* renamed from: c  reason: collision with root package name */
    private HashSet<String> f1144c;

    /* compiled from: MonitorCoreExceptionManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void directReportError(StackTraceElement stackTraceElement, String str, String str2);

        void directReportError(Throwable th, String str);

        void ensureNotReachHere(String str);

        void ensureNotReachHere(Throwable th, String str);

        void ensureNotReachHere(Throwable th, String str, Map<String, String> map);
    }

    private l() {
        this.f1144c = new HashSet<>();
    }

    /* compiled from: MonitorCoreExceptionManager.java */
    /* loaded from: classes.dex */
    private static class b {

        /* renamed from: a  reason: collision with root package name */
        private static final l f1146a = new l();

        private b() {
        }
    }

    public static l getInstance() {
        return b.f1146a;
    }

    public void setExceptionCallback(a aVar) {
        this.f1143b = aVar;
        a();
    }

    private void a() {
        com.bytedance.monitor.util.thread.c.getAsyncTaskManagerInstance().setExceptionListener(new d.a() { // from class: com.bytedance.apm.l.1
            @Override // com.bytedance.monitor.util.thread.d.a
            public void directReportError(Throwable th, String str) {
                if (l.this.f1143b != null) {
                    l.this.f1143b.directReportError(th, str);
                }
            }
        });
    }

    public void ensureNotReachHere(Throwable th, String str) {
        if (this.f1143b != null && !this.f1144c.contains(str)) {
            this.f1144c.add(str);
            this.f1143b.ensureNotReachHere(th, f1142a + str);
        }
        if (c.isDebugMode()) {
            th.printStackTrace();
        }
    }

    public void directReportError(Throwable th, String str) {
        try {
            a aVar = this.f1143b;
            if (aVar != null) {
                aVar.directReportError(th, str);
            }
        } catch (Throwable unused) {
        }
        if (c.isDebugMode() || c.isLocalChannel()) {
            throw new RuntimeException(th);
        }
    }

    public void ensureNotReachHere(String str) {
        if (this.f1143b != null && !this.f1144c.contains(str)) {
            this.f1144c.add(str);
            this.f1143b.ensureNotReachHere(f1142a + str);
        }
        if (c.isDebugMode()) {
            Log.e(f1142a, "ensureNotReachHere", new RuntimeException(str));
            System.exit(1);
        }
    }

    public void ensureNotReachHere(Throwable th, String str, Map<String, String> map) {
        if (this.f1143b == null || this.f1144c.contains(str)) {
            return;
        }
        this.f1144c.add(str);
        this.f1143b.ensureNotReachHere(th, f1142a + str, map);
    }
}
