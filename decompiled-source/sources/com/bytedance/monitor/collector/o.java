package com.bytedance.monitor.collector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import androidx.fragment.app.Fragment;
import com.bytedance.android.bytehook.ByteHook;
import com.bytedance.apm.core.ActivityLifeObserver;
import com.bytedance.apm6.perf.base.model.ThreadStatInfo;
import com.bytedance.monitor.collector.BinderMonitor;
import com.bytedance.monitor.collector.f;
import com.bytedance.monitor.collector.i;
import com.bytedance.services.apm.api.IActivityLifeObserver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;
/* compiled from: PerfMonitorManager.java */
/* loaded from: classes2.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    static volatile boolean f9620a = false;

    /* renamed from: c  reason: collision with root package name */
    private static a f9621c;

    /* renamed from: d  reason: collision with root package name */
    private static volatile o f9622d;

    /* renamed from: b  reason: collision with root package name */
    protected com.bytedance.apm.thread.d f9623b;
    private BinderMonitor n;
    private p o;
    private c p;
    private i q;
    private f r;
    private g s;
    private boolean t;
    private m u;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f9625f = false;

    /* renamed from: g  reason: collision with root package name */
    private volatile boolean f9626g = false;

    /* renamed from: h  reason: collision with root package name */
    private volatile boolean f9627h = false;

    /* renamed from: i  reason: collision with root package name */
    private volatile boolean f9628i = false;

    /* renamed from: j  reason: collision with root package name */
    private volatile boolean f9629j = false;
    private volatile boolean k = false;
    private volatile boolean l = false;
    private boolean m = false;

    /* renamed from: e  reason: collision with root package name */
    private final List<b> f9624e = new CopyOnWriteArrayList();

    /* compiled from: PerfMonitorManager.java */
    /* loaded from: classes2.dex */
    public interface a {
        void loadLibrary(String str);
    }

    public void a(boolean z) {
        this.t = z;
    }

    public static o getInstance() {
        if (f9622d == null) {
            synchronized (o.class) {
                if (f9622d == null) {
                    f9622d = new o();
                }
            }
        }
        return f9622d;
    }

    public static boolean isSoLoaded() {
        return f9620a;
    }

    public static synchronized boolean loadLibrary(Context context) {
        boolean z;
        synchronized (o.class) {
            if (!f9620a) {
                a aVar = f9621c;
                if (aVar != null) {
                    aVar.loadLibrary("monitorcollector-lib");
                    f9620a = true;
                } else {
                    f9620a = com.bytedance.monitor.util.jni.b.loadLibrary(context, "monitorcollector-lib");
                }
                ByteHook.init();
            }
            z = f9620a;
        }
        return z;
    }

    public boolean d() {
        return this.f9625f;
    }

    public synchronized void init(Context context, m mVar) {
        if (this.f9625f) {
            if (com.bytedance.apm.c.isDebugMode()) {
                Log.w("PerfMonitorManager", "PerfMonitorManager init twice? " + mVar, new Throwable());
            }
            a(mVar);
            return;
        }
        if (loadLibrary(context)) {
            p.init();
            a(mVar);
            this.f9625f = true;
        }
    }

    public synchronized boolean a(m mVar) {
        this.u = mVar;
        if (com.bytedance.apm.c.isDebugMode()) {
            Log.i("PerfMonitorManager", "PerfMonitorManager update config:\n " + mVar);
            b(true);
        }
        if (isSoLoaded()) {
            this.k = mVar.isEnableStackSampling();
            g gVar = this.s;
            if (gVar != null) {
                gVar.enableEvilMethodCollect(!this.f9629j && this.k);
            }
            if (this.o == null) {
                this.o = new p(mVar.getRunMode());
            }
            if (mVar.isEnableBinder()) {
                if (this.n == null) {
                    this.n = new BinderMonitor(mVar.getRunMode());
                }
                this.n.c();
            }
            if (mVar.isEnableAtrace()) {
                if (this.p == null) {
                    this.p = new c(mVar.getRunMode());
                }
                this.p.enableAtrace(mVar.getAtraceTag());
            }
            if (mVar.isEnableLooperMonitor() && this.q == null) {
                this.q = new i(mVar.getRunMode(), false);
            }
            return true;
        }
        return false;
    }

    public m e() {
        return this.u;
    }

    public void f() {
        if (this.f9625f) {
            a(this.u.getAtraceTag());
        }
    }

    public void a(long j2) {
        if (this.f9625f) {
            if (this.p == null) {
                this.p = new c(this.u.getRunMode());
            }
            this.p.enableAtrace(j2);
        }
    }

    public void g() {
        c cVar;
        if (this.f9625f && (cVar = this.p) != null) {
            cVar.disableAtrace();
        }
    }

    public void refreshMonitorConfig(int i2) {
        if (this.f9628i) {
            for (b bVar : this.f9624e) {
                if (bVar != null) {
                    bVar.refreshConfig(i2);
                }
            }
        }
    }

    public static void setSoLoader(a aVar) {
        f9621c = aVar;
    }

    public void onReady() {
        this.f9628i = true;
    }

    private o() {
        ActivityLifeObserver.getInstance().register(new IActivityLifeObserver() { // from class: com.bytedance.monitor.collector.o.1
            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onActivityPause(Activity activity) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onActivityResume(Activity activity) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onActivityStarted(Activity activity) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onChange(Activity activity, Fragment fragment) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onFront(Activity activity) {
                o.this.startStackSampling();
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onBackground(Activity activity) {
                o.this.stopStackSampling();
            }
        });
    }

    public void addCollector(b bVar) {
        if (bVar == null || this.f9624e.contains(bVar)) {
            return;
        }
        this.f9624e.add(bVar);
        if (this.f9626g) {
            bVar.start();
        }
    }

    public f.a getLogInstance() {
        f fVar = this.r;
        if (fVar == null) {
            return null;
        }
        return fVar.getLogInstance();
    }

    public void setHyperMonitor(f fVar) {
        this.r = fVar;
    }

    void a(g gVar) {
        this.s = gVar;
        if (gVar != null) {
            gVar.enableEvilMethodCollect(!this.f9629j && this.k);
        }
    }

    public void startStackSampling() {
        if (this.s == null || !this.k) {
            return;
        }
        this.s.start();
    }

    public void stopStackSampling() {
        if (this.s == null || !this.k) {
            return;
        }
        this.s.stop();
    }

    public void setEvilMethodEnable(boolean z) {
        this.f9629j = z;
    }

    public void removeCollector(b bVar) {
        if (bVar != null) {
            this.f9624e.remove(bVar);
            bVar.destroy();
        }
    }

    public void start() {
        for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
            this.f9624e.get(i2).start();
        }
        this.f9626g = true;
    }

    public void stop() {
        for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
            this.f9624e.get(i2).stop();
        }
        this.f9626g = false;
    }

    void b(boolean z) {
        try {
            if (f9620a) {
                MonitorJni.doSetDebugMode(z);
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void initALogInstance() {
        this.l = true;
        f fVar = this.r;
        if (fVar != null && fVar.getLogInstance() != null) {
            MonitorJni.setAlogInstance(this.r.getLogInstance().getNativeReference());
        }
    }

    void a() {
        if (this.r == null || this.s == null || this.f9629j) {
            return;
        }
        if (this.r.getLogInstance() != null) {
            this.s.init(com.bytedance.apm.c.getContext(), this.r.getLogInstance().getNativeReference());
        }
        startStackSampling();
        if (!this.l || this.r.getLogInstance() == null) {
            return;
        }
        MonitorJni.setAlogInstance(this.r.getLogInstance().getNativeReference());
    }

    public com.bytedance.apm.thread.d getThreadWithHandler() {
        return this.f9623b;
    }

    synchronized void b() {
        if (this.r == null) {
            return;
        }
        if (this.f9627h) {
            return;
        }
        if (this.r.getLogInstance() != null) {
            MonitorJni.setAlogInstance(this.r.getLogInstance().getNativeReference());
        }
        if (this.f9623b == null) {
            com.bytedance.apm.thread.d dVar = new com.bytedance.apm.thread.d("hyper_mode");
            this.f9623b = dVar;
            dVar.start();
        }
        if (this.s != null && !this.f9629j) {
            this.s.start();
        }
        MonitorJni.startHyperMonitor();
        for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
            this.f9624e.get(i2).a();
        }
        this.f9627h = true;
    }

    synchronized void c() {
        if (this.f9627h) {
            MonitorJni.stopHyperMonitor();
            g gVar = this.s;
            if (gVar != null) {
                gVar.stop();
            }
            for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
                this.f9624e.get(i2).b();
            }
            this.f9627h = false;
        }
    }

    public void destory() {
        for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
            this.f9624e.get(i2).destroy();
        }
        this.f9624e.clear();
    }

    public synchronized void b(long j2) {
        LockMonitorManager.setOpenFetchStack(true);
        if (f9620a) {
            try {
                Log.d("LockDetect", "startLockDetect");
                MonitorJni.enableLock(j2);
            } catch (Throwable unused) {
            }
        }
    }

    public synchronized void h() {
        LockMonitorManager.setOpenFetchStack(false);
        if (f9620a) {
            try {
                Log.d("LockDetect", "endLockDetect");
                MonitorJni.disableLock();
            } catch (Throwable unused) {
            }
        }
    }

    public List<BinderMonitor.a> dumpBinderInfo() {
        BinderMonitor binderMonitor = this.n;
        if (binderMonitor != null) {
            return binderMonitor.getBinderInfoListCopy();
        }
        return null;
    }

    public JSONObject dumpInfos() {
        JSONObject jSONObject = new JSONObject();
        for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
            try {
                Pair<String, ?> dumpInfo = this.f9624e.get(i2).dumpInfo();
                jSONObject.put((String) dumpInfo.first, dumpInfo.second);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    public JSONObject dumpInfos(long j2, long j3, boolean z) {
        JSONObject jSONObject = new JSONObject();
        for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
            try {
                b bVar = this.f9624e.get(i2);
                if (!z || !(bVar instanceof i)) {
                    Pair<String, ?> dumpInfosRange = bVar.dumpInfosRange(j2, j3);
                    jSONObject.put((String) dumpInfosRange.first, dumpInfosRange.second);
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    public JSONObject dumpInfos(long j2, long j3) {
        return dumpInfos(j2, j3, false);
    }

    public long a(int i2) {
        if (!this.t) {
            return com.bytedance.apm.util.d.getTotalCPUTimeByTimeInStat();
        }
        if (isSoLoaded()) {
            try {
                return MonitorJni.getTotalCPUTimeByTimeInStat(i2);
            } catch (Throwable unused) {
                return 0L;
            }
        }
        return 0L;
    }

    public long i() {
        if (!this.t) {
            return com.bytedance.apm.util.d.getAppCPUTime();
        }
        if (isSoLoaded()) {
            try {
                return MonitorJni.doGetAppCpuTime();
            } catch (Throwable unused) {
                return 0L;
            }
        }
        return 0L;
    }

    public ThreadStatInfo a(int i2, int i3) {
        return MonitorJni.getThreadStatInfo(i2, i3);
    }

    public int b(int i2) {
        return MonitorJni.getProcCGroup(i2);
    }

    public boolean a(String str, int[] iArr, Object[] objArr, long[] jArr, float[] fArr) {
        return MonitorJni.readProcFile(str, iArr, objArr, jArr, fArr);
    }

    public String dumpStackTrace(long j2, long j3) {
        if (this.s == null || this.f9629j) {
            return null;
        }
        return this.s.dumpStack(j2, j3);
    }

    public String c(int i2) {
        return MonitorJni.doDumpNativeStack(i2);
    }

    public String dumpSortedStackTrace(long j2, long j3) {
        if (this.s == null || this.f9629j) {
            return null;
        }
        return q.getStackString(this.s.dumpStack(j2, j3));
    }

    public void dumpInfoToALog(long j2, long j3, long j4) {
        if (this.f9627h) {
            return;
        }
        for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
            try {
                b bVar = this.f9624e.get(i2);
                if (!(bVar instanceof i)) {
                    bVar.a(j2, j3, j4);
                }
            } catch (Exception unused) {
            }
        }
    }

    public Map<String, String> dumpInfosAsMap() {
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < this.f9624e.size(); i2++) {
            try {
                Pair<String, ?> dumpInfo = this.f9624e.get(i2).dumpInfo();
                hashMap.put(dumpInfo.first, String.valueOf(dumpInfo.second));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    public void setProcALogMode(boolean z) {
        p pVar = this.o;
        if (pVar != null) {
            pVar.setALogCollectMode(z);
        }
    }

    public i.c getFrameCallback() {
        i iVar = this.q;
        if (iVar == null) {
            return null;
        }
        return iVar.f9538f;
    }

    public i.e getLastMessageItem() {
        i iVar = this.q;
        if (iVar == null) {
            return null;
        }
        return iVar.getLastItem();
    }

    public i getLooperDispatchMonitor() {
        return this.q;
    }
}
