package com.bytedance.platform.godzilla.common;

import android.os.Handler;
import android.os.Looper;
/* compiled from: ActivityThreadHelper.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final long f11044a = 5000;

    /* renamed from: b  reason: collision with root package name */
    private static Class f11045b;

    /* renamed from: c  reason: collision with root package name */
    private static Object f11046c;

    private b() {
    }

    public static Object currentActivityThread() {
        if (f11046c == null) {
            try {
                synchronized (b.class) {
                    if (f11046c == null) {
                        if (f11045b == null) {
                            f11045b = Class.forName("android.app.ActivityThread");
                        }
                        f11046c = com.bytedance.platform.godzilla.utils.e.invokeStaticMethod(f11045b, "currentActivityThread", new Object[0]);
                    }
                    if (f11046c == null && Looper.myLooper() != Looper.getMainLooper()) {
                        final Object obj = new Object();
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.platform.godzilla.common.b.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    Object unused = b.f11046c = com.bytedance.platform.godzilla.utils.e.invokeStaticMethod(b.f11045b, "currentActivityThread", new Object[0]);
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
                        if (f11046c == null) {
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
        return f11046c;
    }
}
