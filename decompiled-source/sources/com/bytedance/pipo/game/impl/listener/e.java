package com.bytedance.pipo.game.impl.listener;
/* compiled from: BillingSetUpListener.java */
/* loaded from: classes2.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.pipo.game.impl.monitor.f f10338a;

    public void b() {
        com.bytedance.pipo.game.impl.monitor.f fVar = this.f10338a;
        if (fVar != null) {
            fVar.a();
        }
    }

    public void a() {
        com.bytedance.pipo.game.impl.monitor.f fVar = this.f10338a;
        if (fVar != null) {
            fVar.a(true, 0);
        }
    }

    public void a(com.bytedance.pipo.game.impl.model.e eVar) {
        com.bytedance.pipo.game.impl.monitor.f fVar = this.f10338a;
        if (fVar != null) {
            fVar.a(false, 4012, eVar);
        }
    }
}
