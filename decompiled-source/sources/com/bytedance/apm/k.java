package com.bytedance.apm;

import java.util.List;
/* compiled from: LooperMonitorApi.java */
/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static a f1141a;

    /* compiled from: LooperMonitorApi.java */
    /* loaded from: classes.dex */
    public interface a {
        List<com.bytedance.crash.entity.f> dumpMainLooperHistoryMsg();

        com.bytedance.crash.entity.f getDispatchingMsg();
    }

    public static a getLooperMonitorImpl() {
        return f1141a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(a aVar) {
        f1141a = aVar;
    }
}
