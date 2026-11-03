package com.bytedance.android.service.manager.push.monitor;

import android.content.Context;
import android.util.Log;
/* loaded from: classes.dex */
public class IPushSdkMonitorServiceImplOfMock implements IPushSdkMonitorService {
    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void initOnApplication(Context context) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onBadgeOperateFailed(int i2, Integer num, String str) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onBadgeOperateSuccess(int i2, Integer num) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onContentRequest(String str, int i2, int i3) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onContentShow(String str, String str2, int i2) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onKeepAliveFrom(String str, String str2, int i2) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onLaunchEvent(boolean z, boolean z2, long j2, long j3, int i2, int i3, String str) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onMessageArrive(long j2) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onMessageShow(long j2, long j3) {
    }

    @Override // com.bytedance.push.monitor.i
    public void onRequestFailed(String str, String str2, String str3, String str4, long j2) {
    }

    @Override // com.bytedance.push.monitor.i
    public void onRequestSuccess(String str, String str2, String str3, long j2) {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public void onTransmitStrategyRequest() {
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public boolean curIsDau() {
        Log.w("IPushSdkMonitorService", "cur curIsDau method is empty impl in IPushSdkMonitorServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService
    public boolean curIsTransmit() {
        Log.w("IPushSdkMonitorService", "cur curIsTransmit method is empty impl in IPushSdkMonitorServiceImplOfMockClass");
        return false;
    }
}
