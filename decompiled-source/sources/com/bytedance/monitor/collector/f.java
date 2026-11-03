package com.bytedance.monitor.collector;
/* compiled from: IHyperMonitor.java */
/* loaded from: classes2.dex */
public interface f {

    /* compiled from: IHyperMonitor.java */
    /* loaded from: classes2.dex */
    public interface a {
        void d(String str, String str2);

        void e(String str, String str2);

        long getNativeReference();

        void i(String str, String str2);

        void v(String str, String str2);

        void w(String str, String str2);
    }

    a getLogInstance();
}
