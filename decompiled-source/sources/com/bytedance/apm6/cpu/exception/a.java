package com.bytedance.apm6.cpu.exception;

import com.meituan.robust.Constants;
/* compiled from: BaseCpuExceptionState.java */
/* loaded from: classes.dex */
public abstract class a implements g {

    /* renamed from: b  reason: collision with root package name */
    protected static final boolean f2042b = false;

    /* renamed from: a  reason: collision with root package name */
    protected d f2043a;

    public a(d dVar) {
        this.f2043a = dVar;
    }

    @Override // com.bytedance.apm6.cpu.exception.g
    public void onEnterThisState(com.bytedance.apm6.cpu.config.c cVar, boolean z) {
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.i(com.bytedance.apm6.cpu.d.TAG, "enter : " + provideType());
        }
    }

    @Override // com.bytedance.apm6.cpu.exception.g
    public void onStopDetect() {
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.i(com.bytedance.apm6.cpu.d.TAG, "stop detect when state is : " + provideType());
        }
    }

    @Override // com.bytedance.apm6.cpu.exception.g
    public void onLifeCycleChange(boolean z) {
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.i(com.bytedance.apm6.cpu.d.TAG, "onLifeCycleChange when state is : " + provideType());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str) {
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.i(com.bytedance.apm6.cpu.d.TAG, Constants.ARRAY_TYPE + provideType() + "]: " + str);
        }
    }
}
