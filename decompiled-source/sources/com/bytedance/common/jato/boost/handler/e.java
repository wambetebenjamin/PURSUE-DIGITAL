package com.bytedance.common.jato.boost.handler;

import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: ViewRootImplHandlerInjector.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static Method f4590a;

    /* renamed from: b  reason: collision with root package name */
    private static Class f4591b;

    /* renamed from: c  reason: collision with root package name */
    private static Field f4592c;

    public static void a() {
        Method a2 = com.bytedance.common.jato.util.c.a(View.class, "getViewRootImpl", new Class[0]);
        f4590a = a2;
        a2.setAccessible(true);
        Class<?> a3 = com.bytedance.common.jato.util.c.a("android.view.ViewRootImpl");
        f4591b = a3;
        Field a4 = com.bytedance.common.jato.util.c.a(a3, "mHandler");
        f4592c = a4;
        a4.setAccessible(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.view.View r5) {
        /*
            java.lang.reflect.Method r0 = com.bytedance.common.jato.boost.handler.e.f4590a
            if (r0 == 0) goto L42
            java.lang.Class r1 = com.bytedance.common.jato.boost.handler.e.f4591b
            if (r1 == 0) goto L42
            java.lang.reflect.Field r1 = com.bytedance.common.jato.boost.handler.e.f4592c
            if (r1 != 0) goto Ld
            goto L42
        Ld:
            r1 = 0
            r2 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L24
            java.lang.Object r5 = r0.invoke(r5, r1)     // Catch: java.lang.Exception -> L24
            if (r5 != 0) goto L18
            return
        L18:
            java.lang.reflect.Field r0 = com.bytedance.common.jato.boost.handler.e.f4592c     // Catch: java.lang.Exception -> L22
            java.lang.Object r0 = r0.get(r5)     // Catch: java.lang.Exception -> L22
            android.os.Handler r0 = (android.os.Handler) r0     // Catch: java.lang.Exception -> L22
            r2 = r0
            goto L29
        L22:
            r0 = move-exception
            goto L26
        L24:
            r0 = move-exception
            r5 = r2
        L26:
            r0.printStackTrace()
        L29:
            if (r2 == 0) goto L42
            com.bytedance.common.jato.boost.handler.c r0 = new com.bytedance.common.jato.boost.handler.c
            android.os.Looper r1 = r2.getLooper()
            r0.<init>(r1)
            java.lang.reflect.Field r1 = com.bytedance.common.jato.boost.handler.e.f4592c
            long r3 = com.bytedance.common.jato.util.f.a(r1)
            java.lang.String r1 = "VRI_H_"
            r0.a(r1, r2)
            com.bytedance.common.jato.util.f.a(r5, r3, r0)
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.jato.boost.handler.e.a(android.view.View):void");
    }
}
