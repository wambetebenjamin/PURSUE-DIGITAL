package com.bytedance.apm6.cpu.exception.normal;
/* compiled from: NormalIdleState.java */
/* loaded from: classes.dex */
public class e extends com.bytedance.apm6.cpu.exception.a {
    public e(com.bytedance.apm6.cpu.exception.d dVar) {
        super(dVar);
    }

    @Override // com.bytedance.apm6.cpu.exception.a, com.bytedance.apm6.cpu.exception.g
    public void onEnterThisState(com.bytedance.apm6.cpu.config.c cVar, boolean z) {
        super.onEnterThisState(cVar, z);
        this.f2043a.onProcessDetect();
    }

    @Override // com.bytedance.apm6.cpu.exception.a, com.bytedance.apm6.cpu.exception.g
    public void onStopDetect() {
        super.onStopDetect();
    }

    @Override // com.bytedance.apm6.cpu.exception.a, com.bytedance.apm6.cpu.exception.g
    public void onLifeCycleChange(boolean z) {
        super.onLifeCycleChange(z);
        a("life cycle change when state is idle, lifecycle change to back?: " + z);
        this.f2043a.onProcessDetect();
    }

    @Override // com.bytedance.apm6.cpu.exception.g
    public com.bytedance.apm6.cpu.exception.h provideType() {
        return com.bytedance.apm6.cpu.exception.h.IDLE;
    }
}
