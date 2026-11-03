package com.bytedance.sdk.account.impl;
/* compiled from: BDAccountFrameworkManager.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static volatile g f12421a;

    public static g a() {
        if (f12421a == null) {
            synchronized (g.class) {
                if (f12421a == null) {
                    f12421a = new g();
                }
            }
        }
        return f12421a;
    }

    private g() {
    }

    public void b() {
        a(com.bytedance.sdk.account.bus.c.f12316a, com.bytedance.sdk.account.bus.b.f12315a);
        com.bytedance.sdk.account.error.handler.b.a(new com.bytedance.sdk.account.error.handler.impl.a());
    }

    public void a(String str, com.bytedance.sdk.account.bus.e eVar) {
        com.bytedance.sdk.account.bus.c.f12317b.a(str, eVar);
    }
}
