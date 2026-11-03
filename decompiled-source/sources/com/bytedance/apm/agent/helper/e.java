package com.bytedance.apm.agent.helper;
/* compiled from: TraceDebug.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static a f225a;

    /* compiled from: TraceDebug.java */
    /* loaded from: classes.dex */
    public interface a {
        void log(String str, long j2);
    }

    public static void setLogListener(a aVar) {
        f225a = aVar;
    }

    public static a getLogListener() {
        return f225a;
    }

    public static void notifyLogListener(com.bytedance.apm.agent.tracing.d dVar, long j2) {
        a aVar = f225a;
        if (aVar != null) {
            aVar.log(dVar.methodName, j2);
        }
    }
}
