package com.bytedance.common.wschannel.heartbeat.monitor;
/* compiled from: HeartBeatMonitor.java */
/* loaded from: classes2.dex */
public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private c f5662a;

    private b() {
    }

    /* compiled from: HeartBeatMonitor.java */
    /* loaded from: classes2.dex */
    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        private static final b f5663a = new b();

        private a() {
        }
    }

    public static b a() {
        return a.f5663a;
    }

    public void a(c cVar) {
        this.f5662a = cVar;
    }

    @Override // com.bytedance.common.wschannel.heartbeat.monitor.c
    public void b() {
        c cVar = this.f5662a;
        if (cVar != null) {
            cVar.b();
        }
    }

    @Override // com.bytedance.common.wschannel.heartbeat.monitor.c
    public void c() {
        c cVar = this.f5662a;
        if (cVar != null) {
            cVar.c();
        }
    }

    @Override // com.bytedance.common.wschannel.heartbeat.monitor.c
    public void a(Throwable th) {
        c cVar = this.f5662a;
        if (cVar != null) {
            cVar.a(th);
        }
    }
}
