package com.bytedance.apm.agent.instrumentation;

import com.bytedance.apm.util.ai;
/* compiled from: ThreadMonitor.java */
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static final String f244a = "ThreadMonitor";

    public static void sleepMonitor(long j2) {
        if (ai.isMainThread() && j2 > 10 && !a()) {
            b();
        }
        Thread.sleep(j2);
    }

    private static boolean a() {
        return com.bytedance.apm.util.b.isDebug(com.bytedance.apm.c.getContext());
    }

    private static boolean b() {
        return com.bytedance.apm.c.isLocalChannel();
    }
}
