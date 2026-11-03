package com.bytedance.crash.resource.gwpasan;
/* compiled from: GwpAsanAdapter.java */
/* loaded from: classes2.dex */
public class a {
    private static a r;

    public static void g() {
    }

    public static a f() {
        if (r == null) {
            synchronized (a.class) {
                if (r == null) {
                    r = new a();
                }
            }
        }
        return r;
    }
}
