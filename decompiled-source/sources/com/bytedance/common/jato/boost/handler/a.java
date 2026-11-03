package com.bytedance.common.jato.boost.handler;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import com.bytedance.common.jato.util.f;
import com.meituan.robust.Constants;
import java.lang.reflect.Field;
/* compiled from: ActivityHandlerInjector.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static Class f4575a;

    /* renamed from: b  reason: collision with root package name */
    private static Field f4576b;

    public static void a() {
        Class<?> a2 = com.bytedance.common.jato.util.c.a("android.app.Activity");
        f4575a = a2;
        Field a3 = com.bytedance.common.jato.util.c.a(a2, "mHandler");
        f4576b = a3;
        a3.setAccessible(true);
    }

    public static void a(Activity activity) {
        Field field;
        if (f4575a == null || (field = f4576b) == null) {
            return;
        }
        Handler handler = null;
        try {
            handler = (Handler) field.get(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (handler != null) {
            c cVar = new c(handler.getLooper());
            long a2 = f.a(f4576b);
            cVar.a("ACT_H_", handler);
            f.a(activity, a2, cVar);
        }
    }

    private static boolean a(Activity activity, c cVar) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        boolean a2 = a(activity, "android.app.Activity", "android.app.FragmentController", cVar);
        if (a2) {
            return a(activity, "androidx.fragment.app.FragmentActivity") ? a(activity, "androidx.fragment.app.FragmentActivity", "androidx.fragment.app.FragmentController", cVar) : a2;
        }
        return false;
    }

    private static boolean a(Activity activity, String str, String str2, c cVar) {
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            Class<?> a2 = com.bytedance.common.jato.util.c.a(str);
            Class<?> a3 = com.bytedance.common.jato.util.c.a(str2);
            Field a4 = com.bytedance.common.jato.util.c.a(a2, "mFragments");
            a4.setAccessible(true);
            Object obj = a4.get(activity);
            Field a5 = com.bytedance.common.jato.util.c.a(a3, "mHost");
            a5.setAccessible(true);
            Object obj2 = a5.get(obj);
            if (((Handler) com.bytedance.common.jato.util.c.a(obj2, "mHandler")) == cVar.a()) {
                f.a(obj2, f.a(f4576b), cVar);
            }
            return true;
        } catch (Exception e2) {
            Log.e("SimpleHandler", "inject " + str + Constants.ARRAY_TYPE + (SystemClock.uptimeMillis() - uptimeMillis) + "ms] Err", e2);
            return false;
        }
    }

    private static boolean a(Activity activity, String str) {
        try {
            Class<?> cls = Class.forName(str);
            if (cls != null) {
                return activity.getClass().isAssignableFrom(cls);
            }
            return false;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
