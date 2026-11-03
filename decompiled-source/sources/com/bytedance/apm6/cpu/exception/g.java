package com.bytedance.apm6.cpu.exception;
/* compiled from: ICpuExceptionState.java */
/* loaded from: classes.dex */
public interface g {
    void onEnterThisState(com.bytedance.apm6.cpu.config.c cVar, boolean z);

    void onLifeCycleChange(boolean z);

    void onStopDetect();

    h provideType();
}
