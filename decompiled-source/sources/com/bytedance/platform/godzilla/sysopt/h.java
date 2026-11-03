package com.bytedance.platform.godzilla.sysopt;

import android.app.Application;
/* compiled from: PthreadCreateHookPlugin.java */
/* loaded from: classes2.dex */
public class h extends com.bytedance.platform.godzilla.plugin.a {

    /* renamed from: a  reason: collision with root package name */
    private int f11076a;

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void destroy() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "PthreadCreateHookPlugin";
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void init(Application application) {
    }

    public h(int i2) {
        this.f11076a = 1048576;
        this.f11076a = i2;
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        PthreadCreateHook.start(this.f11076a);
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
        PthreadCreateHook.end();
    }
}
