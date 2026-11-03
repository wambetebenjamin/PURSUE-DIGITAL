package com.bytedance.apm6.memory;

import android.app.Activity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: MemoryCollector.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2303a = "c";

    /* renamed from: b  reason: collision with root package name */
    private static final long f2304b = 30000;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f2305c;

    /* renamed from: d  reason: collision with root package name */
    private volatile com.bytedance.apm.listener.c f2306d;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.apm6.memory.config.a f2307e;

    /* renamed from: f  reason: collision with root package name */
    private com.bytedance.apm6.util.timetask.a f2308f;

    /* renamed from: g  reason: collision with root package name */
    private volatile boolean f2309g;

    /* renamed from: h  reason: collision with root package name */
    private long f2310h;

    /* renamed from: i  reason: collision with root package name */
    private b f2311i;

    /* renamed from: j  reason: collision with root package name */
    private Map<Object, Object> f2312j;
    private List<com.bytedance.apm6.memory.listener.a> k;
    private volatile boolean l;

    private c() {
        this.f2310h = 0L;
        this.f2311i = new b();
        this.k = new CopyOnWriteArrayList();
    }

    public static c getInstance() {
        return a.f2314a;
    }

    public void addMapInfo(Map<Object, Object> map) {
        if (map == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        this.f2312j = hashMap;
        hashMap.putAll(map);
    }

    public void addMemoryCallBackListener(com.bytedance.apm6.memory.listener.a aVar) {
        if (aVar != null) {
            this.k.add(aVar);
        }
    }

    public void removeMemoryCallBackListener(com.bytedance.apm6.memory.listener.a aVar) {
        if (aVar != null) {
            this.k.remove(aVar);
        }
    }

    public final synchronized void init() {
        if (this.f2305c) {
            return;
        }
        this.f2305c = true;
        updateConfig(((com.bytedance.apm6.memory.config.b) com.bytedance.apm6.service.d.getService(com.bytedance.apm6.memory.config.b.class)).getConfig());
    }

    public void initGCInfo() {
        e.getInstance().a();
    }

    public void g() {
        this.l = false;
    }

    public void h() {
        this.l = true;
    }

    public synchronized void updateConfig(com.bytedance.apm6.memory.config.a aVar) {
        if (aVar == null) {
            return;
        }
        this.f2307e = aVar;
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d("APM-Memory", "updateConfig: " + aVar);
        }
        if (!aVar.isApm6SampleEnable()) {
            b();
            com.bytedance.apm6.service.lifecycle.a aVar2 = (com.bytedance.apm6.service.lifecycle.a) com.bytedance.apm6.service.d.getService(com.bytedance.apm6.service.lifecycle.a.class);
            if (aVar2 != null) {
                aVar2.unregister(this.f2311i);
            }
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d("APM-Memory", "isApm6SampleEnable: " + aVar.isApm6SampleEnable());
            }
            return;
        }
        com.bytedance.apm6.service.lifecycle.a aVar3 = (com.bytedance.apm6.service.lifecycle.a) com.bytedance.apm6.service.d.getService(com.bytedance.apm6.service.lifecycle.a.class);
        if (aVar3 != null) {
            if (aVar.isStopWhenBackground()) {
                aVar3.unregister(this.f2311i);
                aVar3.register(this.f2311i);
            } else {
                aVar3.unregister(this.f2311i);
            }
        }
        long memoryCollectionInterval = aVar.memoryCollectionInterval() * 1000;
        this.f2310h = memoryCollectionInterval;
        if (memoryCollectionInterval < 30000) {
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d("APM-Memory", "pollingIntervalMillis: " + this.f2310h);
            }
            this.f2310h = 30000L;
        }
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d("APM-Memory", "result pollingIntervalMillis: " + this.f2310h);
        }
        com.bytedance.apm6.util.timetask.a aVar4 = this.f2308f;
        if (aVar4 != null && aVar4.getLoopInterval() != this.f2310h) {
            b();
        }
        a();
        com.bytedance.apm6.memory.b bVar = (com.bytedance.apm6.memory.b) com.bytedance.apm6.service.d.getService(com.bytedance.apm6.memory.b.class);
        if (bVar != null) {
            bVar.start();
        }
    }

    public void registerReachTopListener(com.bytedance.apm.listener.c cVar) {
        this.f2306d = cVar;
    }

    public void unregisterReachTopListener() {
        this.f2306d = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a() {
        if (!this.f2309g) {
            this.f2309g = true;
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d("APM-Memory", "start");
            }
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b() {
        if (this.f2309g) {
            this.f2309g = false;
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        long currentTimeMillis = System.currentTimeMillis();
        com.bytedance.apm6.memory.monitorable.a a2 = e.getInstance().a(this.f2307e);
        if (a2 == null) {
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d("APM-Memory", "collectWithConfig return is null");
                return;
            }
            return;
        }
        a2.setMmapInfo(this.f2312j);
        this.f2312j = null;
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d("APM-Memory", String.format("collect[isUploadEnable=%s cost=%s]: %s", Boolean.valueOf(this.f2307e.isUploadEnable()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), a2.toJsonObject().toString()));
        }
        if (this.f2307e.isUploadEnable()) {
            a(a2);
        } else if (com.bytedance.apm.logging.a.isFeedbackALogEnabled()) {
            com.bytedance.apm.logging.a.feedbackI(f2303a, a2.toJsonObject().toString());
        }
        if (a2.getJavaUsedMemoryRate() > this.f2307e.memoryTopCheckThreshold() && this.f2306d != null) {
            this.f2306d.onReachTop("reach_top_java");
        }
        List<com.bytedance.apm6.memory.listener.a> list = this.k;
        if (list == null || list.size() <= 0) {
            return;
        }
        try {
            com.bytedance.apm6.memory.bean.a memoryCollectInfo = a2.getMemoryCollectInfo();
            for (com.bytedance.apm6.memory.listener.a aVar : this.k) {
                aVar.onMemoryCallback(memoryCollectInfo);
            }
        } catch (Exception unused) {
        }
    }

    private void d() {
        if (this.f2308f == null) {
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d("APM-Memory", "scheduleCollectMemory");
            }
            this.f2308f = new com.bytedance.apm6.util.timetask.a(0L, this.f2310h) { // from class: com.bytedance.apm6.memory.c.1
                @Override // java.lang.Runnable
                public void run() {
                    if (c.this.l) {
                        return;
                    }
                    c.this.c();
                }
            };
            com.bytedance.apm6.util.timetask.b.getInstance(com.bytedance.apm6.util.timetask.c.LIGHT_WEIGHT).sendTask(this.f2308f);
        }
    }

    private void e() {
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d("APM-Memory", "cancelCollectMemory");
        }
        if (this.f2308f != null) {
            com.bytedance.apm6.util.timetask.b.getInstance(com.bytedance.apm6.util.timetask.c.LIGHT_WEIGHT).removeTask(this.f2308f);
            this.f2308f = null;
        }
    }

    private void a(com.bytedance.apm6.monitor.c cVar) {
        if (cVar == null) {
            return;
        }
        com.bytedance.apm6.monitor.b.record(cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MemoryCollector.java */
    /* loaded from: classes.dex */
    public class b extends com.bytedance.apm6.service.lifecycle.b {
        private b() {
        }

        @Override // com.bytedance.apm6.service.lifecycle.b, com.bytedance.apm6.service.lifecycle.c
        public final void onFront(Activity activity) {
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d("APM-Memory", "isStopWhenBackground:" + c.this.f());
            }
            if (c.this.f()) {
                c.this.a();
            }
        }

        @Override // com.bytedance.apm6.service.lifecycle.b, com.bytedance.apm6.service.lifecycle.c
        public final void onBackground(Activity activity) {
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d("APM-Memory", "isStopWhenBackground:" + c.this.f());
            }
            if (c.this.f()) {
                c.this.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        com.bytedance.apm6.memory.config.a aVar = this.f2307e;
        return aVar != null && aVar.isStopWhenBackground();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MemoryCollector.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final c f2314a = new c();

        private a() {
        }
    }
}
