package com.bytedance.crash.util;
/* compiled from: LogcatUtils.java */
/* loaded from: classes2.dex */
public final class v {

    /* renamed from: a  reason: collision with root package name */
    private static a f7315a;

    /* compiled from: LogcatUtils.java */
    /* loaded from: classes2.dex */
    public static abstract class a {
        abstract String a();

        abstract void a(String str);
    }

    public static void dumpLogcat(String str) {
        a aVar = f7315a;
        if (aVar != null) {
            aVar.a(str);
        }
    }

    public static void setLogcatImpl(a aVar) {
        f7315a = aVar;
    }
}
