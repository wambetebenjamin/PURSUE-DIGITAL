package com.bytedance.monitor.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
/* compiled from: ActivityThreadUtils.java */
/* loaded from: classes2.dex */
public class a {
    public static final int BIND_SERVICE = 121;
    public static final int CREATE_SERVICE = 114;
    public static final int DUMP_SERVICE = 123;
    public static final int EXECUTE_TRANSACTION = 159;
    public static final int LAUNCH_ACTIVITY = 100;
    public static final int PAUSE_ACTIVITY = 101;
    public static final int RECEIVER = 113;
    public static final int SERVICE_ARGS = 115;
    public static final int STOP_SERVICE = 116;
    public static final int UNBIND_SERVICE = 122;

    /* renamed from: a  reason: collision with root package name */
    private static Class f9660a = null;

    /* renamed from: b  reason: collision with root package name */
    private static Class f9661b = null;

    /* renamed from: c  reason: collision with root package name */
    private static Field f9662c = null;

    /* renamed from: d  reason: collision with root package name */
    private static Field f9663d = null;

    /* renamed from: e  reason: collision with root package name */
    private static Field f9664e = null;

    /* renamed from: f  reason: collision with root package name */
    private static Object f9665f = null;

    /* renamed from: g  reason: collision with root package name */
    private static Object f9666g = null;

    /* renamed from: h  reason: collision with root package name */
    private static Object f9667h = null;

    /* renamed from: i  reason: collision with root package name */
    private static Object f9668i = null;

    /* renamed from: j  reason: collision with root package name */
    private static Set<Integer> f9669j = null;
    private static final long k = 5000;
    private static Class l;

    static {
        HashSet hashSet = new HashSet();
        f9669j = hashSet;
        hashSet.add(113);
        f9669j.add(114);
        f9669j.add(115);
        f9669j.add(116);
        f9669j.add(121);
        f9669j.add(122);
        f9669j.add(100);
        f9669j.add(159);
        f9669j.add(123);
    }

    public static boolean isMessageNeedInterrupt(Message message) {
        return message != null && f9669j.contains(Integer.valueOf(message.what));
    }

    public static synchronized Class getActivityThread$HClass() {
        Class cls;
        synchronized (a.class) {
            if (f9660a == null) {
                try {
                    f9660a = Class.forName("android.app.ActivityThread$H");
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            cls = f9660a;
        }
        return cls;
    }

    public static synchronized Class getActivityThreadClass() {
        Class cls;
        synchronized (a.class) {
            if (f9661b == null) {
                try {
                    f9661b = Class.forName("android.app.ActivityThread");
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            cls = f9661b;
        }
        return cls;
    }

    public static synchronized Field getHField() {
        Field field;
        synchronized (a.class) {
            if (f9662c == null) {
                f9662c = b.getField(getActivityThreadClass(), "mH");
            }
            field = f9662c;
        }
        return field;
    }

    public static synchronized Field getInstrumentationField() {
        Field field;
        synchronized (a.class) {
            if (f9663d == null) {
                f9663d = b.getField(getActivityThreadClass(), "mInstrumentation");
            }
            field = f9663d;
        }
        return field;
    }

    public static synchronized Field getAppThreadField() {
        Field field;
        synchronized (a.class) {
            if (f9664e == null) {
                f9664e = b.getField(getActivityThreadClass(), "mAppThread");
            }
            field = f9664e;
        }
        return field;
    }

    public static synchronized Object getsActivityThread() {
        Object obj;
        synchronized (a.class) {
            try {
                if (f9667h == null) {
                    f9667h = e.invokeStaticMethod(getActivityThreadClass(), "currentActivityThread", new Object[0]);
                }
                obj = f9667h;
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return null;
            }
        }
        return obj;
    }

    public static synchronized Object getHInActivityThread() {
        Object obj;
        synchronized (a.class) {
            if (f9668i == null) {
                try {
                    f9668i = getHField().get(getsActivityThread());
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
            obj = f9668i;
        }
        return obj;
    }

    public static synchronized Object getInstrumentation() {
        Object obj;
        synchronized (a.class) {
            if (f9665f == null) {
                try {
                    f9665f = getInstrumentationField().get(getsActivityThread());
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
            obj = f9665f;
        }
        return obj;
    }

    public static synchronized Object getAppThread() {
        Object obj;
        synchronized (a.class) {
            if (f9666g == null) {
                try {
                    f9666g = getAppThreadField().get(getsActivityThread());
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
            obj = f9666g;
        }
        return obj;
    }

    public static Object currentActivityThread() {
        if (f9667h == null) {
            try {
                synchronized (a.class) {
                    if (f9667h == null) {
                        if (l == null) {
                            l = Class.forName("android.app.ActivityThread");
                        }
                        f9667h = e.invokeStaticMethod(l, "currentActivityThread", new Object[0]);
                    }
                    if (f9667h == null && Looper.myLooper() != Looper.getMainLooper()) {
                        final Object obj = new Object();
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.monitor.util.a.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    Object unused = a.f9667h = e.invokeStaticMethod(a.l, "currentActivityThread", new Object[0]);
                                    synchronized (obj) {
                                        obj.notify();
                                    }
                                } catch (Exception unused2) {
                                    synchronized (obj) {
                                        obj.notify();
                                    }
                                } catch (Throwable th) {
                                    synchronized (obj) {
                                        obj.notify();
                                        throw th;
                                    }
                                }
                            }
                        });
                        if (f9667h == null) {
                            synchronized (obj) {
                                try {
                                    obj.wait(5000L);
                                } catch (InterruptedException unused) {
                                }
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return f9667h;
    }
}
