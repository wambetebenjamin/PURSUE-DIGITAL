package com.bytedance.push.monitor.opentracing;
/* compiled from: ITracingMonitor.java */
/* loaded from: classes2.dex */
public interface a extends com.ss.android.ug.bus.a {
    void endInit();

    void endRegisterSender(boolean z, int i2, int i3, String str);

    void endRequestSender(boolean z, int i2, String str);

    void endSenderCheck(String str, boolean z);

    void start();

    void startInit();

    void startRegisterSender(int i2);

    void startRequestSender();

    void startSenderCheck(String str, boolean z);
}
