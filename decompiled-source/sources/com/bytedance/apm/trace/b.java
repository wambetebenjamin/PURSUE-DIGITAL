package com.bytedance.apm.trace;

import android.content.Context;
import android.util.Pair;
import com.bytedance.apm.block.evil.EvilMethodSwitcher;
import com.bytedance.apm.constant.r;
import com.bytedance.apm.launch.g;
import com.bytedance.monitor.collector.LockMonitorManager;
import com.bytedance.monitor.collector.o;
import org.json.JSONObject;
/* compiled from: LaunchTrace.java */
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1506a = "AppStartStats";

    /* renamed from: b  reason: collision with root package name */
    private static f f1507b = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f1508c = false;

    /* renamed from: d  reason: collision with root package name */
    private static final int f1509d = 30;

    @Deprecated
    public static void endTask(String str) {
    }

    @Deprecated
    public static void startAndEndTask(String str, long j2, long j3) {
    }

    @Deprecated
    public static void startTask(String str) {
    }

    private b() {
    }

    public static void startTrace() {
        f fVar = new f("start_trace", r.START_STATS_NAME);
        f1507b = fVar;
        fVar.startTrace();
        if (com.bytedance.apm.c.isMainProcessSimple()) {
            boolean isOpenLaunchEvilMethod = EvilMethodSwitcher.isOpenLaunchEvilMethod();
            f1508c = isOpenLaunchEvilMethod;
            if (isOpenLaunchEvilMethod) {
                com.bytedance.apm.launch.evil.b.startMonitorEvilMethod();
            }
        }
    }

    public static void startSpan(String str, String str2) {
        f fVar = f1507b;
        if (fVar != null) {
            fVar.startSpan(str, str2);
        }
    }

    public static void startSpan(String str, String str2, boolean z) {
        f fVar = f1507b;
        if (fVar != null) {
            fVar.startSpan(str, str2, z);
        }
    }

    public static void endSpan(String str, String str2) {
        f fVar = f1507b;
        if (fVar != null) {
            fVar.endSpan(str, str2);
        }
    }

    public static void recordSpan(String str, String str2, long j2) {
        f fVar = f1507b;
        if (fVar != null) {
            fVar.recordSpan(str, str2, j2);
        }
    }

    public static void endTrace(int i2, String str, long j2) {
        endTrace(i2, str, j2, 0L);
    }

    public static void endTrace(int i2, String str, long j2, long j3) {
        if (f1508c) {
            com.bytedance.apm.launch.evil.b.stopMonitorEvilMethod();
        }
        f fVar = f1507b;
        if (fVar != null) {
            fVar.endTrace(i2, str, j2, j3);
        }
    }

    public static void endTrace(String str, String str2, long j2) {
        endTrace(str, str2, j2, 0L);
    }

    public static void endTrace(String str, String str2, long j2, long j3) {
        if (f1508c) {
            com.bytedance.apm.launch.evil.b.stopMonitorEvilMethod();
        }
        f fVar = f1507b;
        if (fVar != null) {
            fVar.endTrace(str, str2, j2, j3);
        }
    }

    public static void cancelTrace() {
        if (f1508c) {
            com.bytedance.apm.launch.evil.b.stopMonitorEvilMethod();
        }
        f fVar = f1507b;
        if (fVar != null) {
            fVar.cancelTrace();
            f1507b = null;
        }
    }

    @Deprecated
    public static void startDetect(Context context, a aVar) {
        if (com.bytedance.apm.c.isMainProcessSimple()) {
            com.bytedance.apm.launch.d.startDetect(context, aVar);
        }
    }

    public static Pair<com.bytedance.apm.launch.a, Long> getDefaultLaunchMode(String str) {
        return g.getLaunchMode(str);
    }

    /* compiled from: LaunchTrace.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private boolean f1511a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f1512b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f1513c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f1514d;

        private a(C0022a c0022a) {
            this.f1511a = c0022a.f1515a;
            this.f1512b = c0022a.f1516b;
            this.f1513c = c0022a.f1517c;
            this.f1514d = c0022a.f1518d;
        }

        public boolean isDetectLongSleep() {
            return this.f1511a;
        }

        public boolean isDetectLock() {
            return this.f1512b;
        }

        public boolean isDetectBinder() {
            return this.f1513c;
        }

        public boolean isDetectOneMinLock() {
            return this.f1514d;
        }

        /* compiled from: LaunchTrace.java */
        /* renamed from: com.bytedance.apm.trace.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0022a {

            /* renamed from: a  reason: collision with root package name */
            private boolean f1515a;

            /* renamed from: b  reason: collision with root package name */
            private boolean f1516b;

            /* renamed from: c  reason: collision with root package name */
            private boolean f1517c;

            /* renamed from: d  reason: collision with root package name */
            private boolean f1518d;

            public C0022a detectLongSleep() {
                this.f1515a = true;
                return this;
            }

            public C0022a detectLock() {
                this.f1516b = true;
                return this;
            }

            public C0022a detectOneMinLock() {
                this.f1518d = true;
                return this;
            }

            public C0022a detectBinder() {
                this.f1517c = true;
                return this;
            }

            public a build() {
                return new a(this);
            }
        }
    }

    public static long getTraceStartTime() {
        f fVar = f1507b;
        if (fVar == null) {
            return 0L;
        }
        return fVar.getTraceBeginTimestamp();
    }

    @Deprecated
    public static void setLaunchCollectExtraInfoTimeMs(long j2) {
        com.bytedance.apm.data.pipeline.d.setLaunchCollectExtraInfoTimeMs(j2);
    }

    @Deprecated
    public static void setLaunchCollectExtraInfoFlag(int i2) {
        boolean z = true;
        if ((i2 & 1) != 0 && !com.bytedance.apm.launch.b.getInstance().getConfig().isNeedCollectNetData()) {
            z = false;
        }
        com.bytedance.apm.data.pipeline.d.setLaunchCollectExtraInfoFlag(z);
    }

    public static boolean a() {
        return a(30L);
    }

    public static synchronized boolean a(final long j2) {
        synchronized (b.class) {
            if (com.bytedance.apm6.foundation.context.a.getContext() == null) {
                return false;
            }
            if (!o.getInstance().d()) {
                com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.trace.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (o.getInstance().d()) {
                            LockMonitorManager.startLockDetect(j2);
                        }
                    }
                });
            } else {
                LockMonitorManager.startLockDetect(j2);
            }
            return true;
        }
    }

    public static void b() {
        LockMonitorManager.endLockDetect(new JSONObject());
    }

    public static synchronized void a(JSONObject jSONObject) {
        synchronized (b.class) {
            LockMonitorManager.endLockDetect(jSONObject);
        }
    }
}
