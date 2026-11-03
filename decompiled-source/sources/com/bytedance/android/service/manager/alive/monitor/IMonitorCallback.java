package com.bytedance.android.service.manager.alive.monitor;
/* loaded from: classes.dex */
public interface IMonitorCallback {
    void onHookActivityManagerResult(boolean z);

    void onHookActivityTaskManagerResult(boolean z);
}
