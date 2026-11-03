package com.bytedance.push.monitor;
/* compiled from: DefaultCommonPushSdkMonitorServiceImpl.java */
/* loaded from: classes2.dex */
public class h implements i {
    @Override // com.bytedance.push.monitor.i
    public void onRequestSuccess(String str, String str2, String str3, long j2) {
        com.bytedance.push.utils.h.w("DefaultCommonPushSdkMonitorServiceImpl", "onRequestSuccess");
    }

    @Override // com.bytedance.push.monitor.i
    public void onRequestFailed(String str, String str2, String str3, String str4, long j2) {
        com.bytedance.push.utils.h.w("DefaultCommonPushSdkMonitorServiceImpl", "onRequestFailed");
    }
}
