package com.bytedance.common.jato.boost.handler;

import android.os.Handler;
import android.view.Choreographer;
import com.bytedance.common.jato.util.f;
import java.lang.reflect.Field;
/* compiled from: ChoreHandlerInjector.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static Field f4577a;

    /* renamed from: b  reason: collision with root package name */
    private static volatile c f4578b;

    public static void a() {
        Field a2 = com.bytedance.common.jato.util.c.a((Class<?>) Choreographer.class, "mHandler");
        f4577a = a2;
        a2.setAccessible(true);
    }

    public static void a(Choreographer choreographer) {
        if (f4577a == null || choreographer == null || f4578b != null) {
            return;
        }
        try {
            Handler handler = (Handler) f4577a.get(choreographer);
            if (handler != null) {
                f4578b = new c(handler.getLooper());
                long a2 = f.a(f4577a);
                f4578b.a("CH_H_", handler);
                f.a(choreographer, a2, f4578b);
            }
        } catch (IllegalAccessException unused) {
        }
    }
}
