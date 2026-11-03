package com.bytedance.apm6.cpu.exception;
/* compiled from: CpuExceptionStateMachine.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2054a = false;

    /* renamed from: b  reason: collision with root package name */
    private boolean f2055b = false;

    /* renamed from: c  reason: collision with root package name */
    private boolean f2056c;

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.apm6.service.lifecycle.a f2057d;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.watson.assist.api.c f2058e;

    /* renamed from: f  reason: collision with root package name */
    private g f2059f;

    /* renamed from: g  reason: collision with root package name */
    private com.bytedance.apm6.cpu.config.c f2060g;

    /* renamed from: h  reason: collision with root package name */
    private g f2061h;

    /* renamed from: i  reason: collision with root package name */
    private g f2062i;

    /* renamed from: j  reason: collision with root package name */
    private g f2063j;
    private g k;
    private g l;

    public d(com.bytedance.apm6.service.lifecycle.a aVar) {
        this.f2057d = aVar;
    }

    public void init(g gVar, g gVar2, g gVar3, g gVar4, g gVar5) {
        if (this.f2055b) {
            return;
        }
        this.f2061h = gVar;
        this.f2062i = gVar2;
        this.f2063j = gVar3;
        this.k = gVar4;
        this.l = gVar5;
        try {
            this.f2058e = com.bytedance.watson.assist.api.b.create(com.bytedance.apm.c.getContext());
        } catch (Throwable unused) {
        }
        this.f2055b = true;
    }

    public synchronized void start(com.bytedance.apm6.cpu.config.c cVar) {
        if (!this.f2054a && this.f2055b) {
            this.f2060g = cVar;
            boolean z = true;
            this.f2054a = true;
            b.initScene(com.bytedance.apm6.cpu.a.getInstance().getCpuSceneString());
            if (this.f2057d.isForeground()) {
                z = false;
            }
            this.f2056c = z;
            onProcessDetect();
        }
    }

    public synchronized void stop() {
        g gVar = this.f2059f;
        if (gVar != null && this.f2054a) {
            gVar.onStopDetect();
            this.f2054a = false;
        }
    }

    public synchronized void onIdle() {
        a(this.l);
    }

    public synchronized void onProcessDetect() {
        a(this.f2061h);
    }

    public synchronized void onProcessDoubleDetect() {
        a(this.f2062i);
    }

    public synchronized void onThreadDetect() {
        a(this.f2063j);
    }

    public synchronized void onCoolDown() {
        a(this.k);
    }

    public synchronized void onLifeCycleChange(boolean z) {
        g gVar = this.f2059f;
        if (gVar != null && this.f2054a) {
            if (this.f2056c == z) {
                return;
            }
            this.f2056c = z;
            gVar.onLifeCycleChange(z);
        }
    }

    public com.bytedance.watson.assist.api.c getAssistStat() {
        return this.f2058e;
    }

    private void a(g gVar) {
        if (this.f2054a && this.f2055b) {
            this.f2059f = gVar;
            com.bytedance.apm6.cpu.config.c cVar = this.f2060g;
            com.bytedance.apm6.service.lifecycle.a aVar = this.f2057d;
            gVar.onEnterThisState(cVar, aVar == null || !aVar.isForeground());
            a();
        }
    }

    private void a() {
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.i(com.bytedance.apm6.cpu.d.TAG, "change cpu exception detect state: " + this.f2059f);
        }
    }
}
