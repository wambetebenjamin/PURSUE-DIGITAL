package com.bytedance.platform.godzilla.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
/* compiled from: ActivityThreadUtils.java */
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static Class f11083a;

    /* renamed from: b  reason: collision with root package name */
    private static Class f11084b;

    /* renamed from: c  reason: collision with root package name */
    private static Field f11085c;

    /* renamed from: d  reason: collision with root package name */
    private static Field f11086d;

    /* renamed from: e  reason: collision with root package name */
    private static Field f11087e;

    /* renamed from: f  reason: collision with root package name */
    private static Object f11088f;

    /* renamed from: g  reason: collision with root package name */
    private static Object f11089g;

    /* renamed from: h  reason: collision with root package name */
    private static Object f11090h;

    /* renamed from: i  reason: collision with root package name */
    private static Object f11091i;

    /* renamed from: j  reason: collision with root package name */
    private static Object f11092j;

    private a() {
    }

    public static synchronized Class getActivityThreadHClass() {
        Class cls;
        synchronized (a.class) {
            if (f11083a == null) {
                try {
                    f11083a = Class.forName("android.app.ActivityThread$H");
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            cls = f11083a;
        }
        return cls;
    }

    public static synchronized Class getActivityThreadClass() {
        Class cls;
        synchronized (a.class) {
            if (f11084b == null) {
                try {
                    f11084b = Class.forName("android.app.ActivityThread");
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            cls = f11084b;
        }
        return cls;
    }

    public static synchronized Field getHField() {
        Field field;
        synchronized (a.class) {
            if (f11085c == null) {
                f11085c = b.getField(getActivityThreadClass(), "mH");
            }
            field = f11085c;
        }
        return field;
    }

    public static synchronized Field getInstrumentationField() {
        Field field;
        synchronized (a.class) {
            if (f11086d == null) {
                f11086d = b.getField(getActivityThreadClass(), "mInstrumentation");
            }
            field = f11086d;
        }
        return field;
    }

    public static synchronized Field getAppThreadField() {
        Field field;
        synchronized (a.class) {
            if (f11087e == null) {
                f11087e = b.getField(getActivityThreadClass(), "mAppThread");
            }
            field = f11087e;
        }
        return field;
    }

    public static synchronized Object getsActivityThread() {
        Object obj;
        synchronized (a.class) {
            try {
                if (f11090h == null) {
                    f11090h = e.invokeStaticMethod(getActivityThreadClass(), "currentActivityThread", new Object[0]);
                }
                obj = f11090h;
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return null;
            }
        }
        return obj;
    }

    public static synchronized Object getHInActivityThread() {
        Object obj;
        synchronized (a.class) {
            if (f11091i == null) {
                try {
                    f11091i = getHField().get(getsActivityThread());
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
            obj = f11091i;
        }
        return obj;
    }

    public static synchronized Object getInstrumentation() {
        Object obj;
        synchronized (a.class) {
            if (f11088f == null) {
                try {
                    f11088f = getInstrumentationField().get(getsActivityThread());
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
            obj = f11088f;
        }
        return obj;
    }

    public static synchronized Object getAppThread() {
        Object obj;
        synchronized (a.class) {
            if (f11089g == null) {
                try {
                    f11089g = getAppThreadField().get(getsActivityThread());
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
            obj = f11089g;
        }
        return obj;
    }

    public static synchronized Object getResourcesManagerInActivityThread() {
        Object obj;
        synchronized (a.class) {
            if (f11092j == null) {
                try {
                    f11092j = b.getField(getActivityThreadClass(), "mResourcesManager").get(getsActivityThread());
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
            obj = f11092j;
        }
        return obj;
    }
}
