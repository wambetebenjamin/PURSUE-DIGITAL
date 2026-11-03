package com.bytedance.frameworks.baselib.network.http;
/* compiled from: OecCallbackGetter.java */
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static a f7939a;

    /* compiled from: OecCallbackGetter.java */
    /* loaded from: classes2.dex */
    public interface a {
        long a();
    }

    public static void a(a aVar) {
        f7939a = aVar;
    }

    public static long a() {
        a aVar = f7939a;
        if (aVar == null) {
            return 0L;
        }
        return aVar.a();
    }
}
