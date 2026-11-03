package com.bytedance.apm6;
/* compiled from: DefaultMapsCollectService.java */
/* loaded from: classes.dex */
class b implements com.bytedance.apm6.memory.b {
    @Override // com.bytedance.apm6.memory.b
    public void stop() {
    }

    @Override // com.bytedance.apm6.memory.b
    public void start() {
        com.bytedance.apm.npth.a.startMonitor();
    }
}
