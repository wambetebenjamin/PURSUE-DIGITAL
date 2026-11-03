package com.bytedance.apm.launch;

import android.util.Log;
import com.bytedance.apm.launch.f;
import com.bytedance.apm.trace.b;
/* compiled from: LaunchAnalysisContext.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1148a = "LaunchAnalysis";

    /* renamed from: b  reason: collision with root package name */
    private f f1149b;

    /* renamed from: c  reason: collision with root package name */
    private b.a f1150c;

    private b() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LaunchAnalysisContext.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private static final b f1151a = new b();

        private a() {
        }
    }

    public static b getInstance() {
        return a.f1151a;
    }

    public synchronized void setConfig(f fVar) {
        this.f1149b = fVar;
    }

    public synchronized f getConfig() {
        if (this.f1149b == null) {
            this.f1149b = new f.a().build();
        }
        return this.f1149b;
    }

    public synchronized void setMonitorConfig(b.a aVar) {
        this.f1150c = aVar;
    }

    public synchronized b.a getMonitorConfig() {
        if (this.f1150c == null) {
            this.f1150c = new b.a.C0022a().build();
        }
        return this.f1150c;
    }

    public void logE(String str) {
        Log.e(f1148a, "notice!!!+ " + str);
    }

    public void logI(String str) {
        Log.i(f1148a, str);
    }

    public void logD(String str) {
        if (com.bytedance.apm.c.isDebugMode()) {
            Log.d(f1148a, str);
        }
    }
}
