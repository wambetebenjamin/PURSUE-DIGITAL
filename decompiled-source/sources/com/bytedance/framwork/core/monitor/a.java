package com.bytedance.framwork.core.monitor;
/* compiled from: FpsMonitor.java */
@Deprecated
/* loaded from: classes2.dex */
public class a {

    /* compiled from: FpsMonitor.java */
    /* renamed from: com.bytedance.framwork.core.monitor.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0121a {
        void fpsCallBack(String str, double d2);
    }

    /* compiled from: FpsMonitor.java */
    /* loaded from: classes2.dex */
    public interface b {
        void onFrame(String str, long j2);
    }

    public static void start(String str) {
    }

    public static void stop() {
    }

    public boolean getMonitorFPSStatus(String str) {
        return false;
    }

    public void setIFPSCallBack(InterfaceC0121a interfaceC0121a) {
    }

    public void setIFrameCallBack(b bVar) {
    }
}
