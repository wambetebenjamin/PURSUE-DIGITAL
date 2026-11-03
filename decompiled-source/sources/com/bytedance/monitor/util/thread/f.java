package com.bytedance.monitor.util.thread;
/* compiled from: ThreadLogListener.java */
/* loaded from: classes2.dex */
public interface f {
    boolean isDebug();

    void onError(String str, String str2);

    void onLog(String str, String str2);
}
