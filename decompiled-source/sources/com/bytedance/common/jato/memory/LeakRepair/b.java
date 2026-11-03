package com.bytedance.common.jato.memory.LeakRepair;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
/* compiled from: ActivityUtils.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static Class f4827a;

    /* renamed from: b  reason: collision with root package name */
    private static Field f4828b;

    static {
        try {
            Class<?> cls = Class.forName("com.android.internal.policy.DecorView");
            f4827a = cls;
            Field a2 = com.bytedance.common.jato.util.c.a(cls, "mContentRoot");
            f4828b = a2;
            a2.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    public static ViewGroup a(Activity activity) {
        if (activity == null || f4828b == null || f4827a == null) {
            return null;
        }
        View decorView = activity.getWindow().getDecorView();
        if (f4827a.isInstance(decorView)) {
            try {
                return (ViewGroup) f4828b.get(decorView);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
