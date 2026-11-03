package com.bytedance.frameworks.baselib.network;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.brentvatne.react.ReactVideoViewManager;
import com.bytedance.common.utility.collection.WeakHandler;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: LifeCycleMonitor.java */
/* loaded from: classes2.dex */
public class b implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f7420a;

    /* renamed from: b  reason: collision with root package name */
    private static WeakHandler f7421b = new WeakHandler(Looper.getMainLooper(), new WeakHandler.IHandler() { // from class: com.bytedance.frameworks.baselib.network.b.1
        @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
        public void handleMsg(Message message) {
        }
    });

    /* renamed from: c  reason: collision with root package name */
    private List<a> f7422c = new CopyOnWriteArrayList();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    public b() {
        if (b() != null) {
            f7420a = true;
        }
    }

    private static Activity b() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(invoke);
            if (map == null) {
                return null;
            }
            for (Object obj : map.values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField(ReactVideoViewManager.PROP_PAUSED);
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(obj);
                }
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(a aVar) {
        this.f7422c.add(aVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (!f7420a) {
            f7420a = true;
            for (a aVar : this.f7422c) {
                aVar.c();
            }
        }
        f7421b.removeCallbacksAndMessages(null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (f7420a) {
            for (a aVar : this.f7422c) {
                f7421b.postDelayed(aVar.a(), aVar.b());
            }
        }
    }

    /* compiled from: LifeCycleMonitor.java */
    /* loaded from: classes2.dex */
    public static abstract class a {

        /* renamed from: a  reason: collision with root package name */
        private int f7423a;

        /* renamed from: b  reason: collision with root package name */
        private Runnable f7424b;

        public abstract void c();

        public abstract void d();

        public a() {
            this.f7423a = 30000;
            this.f7424b = new Runnable() { // from class: com.bytedance.frameworks.baselib.network.b.a.1
                @Override // java.lang.Runnable
                public void run() {
                    if (b.f7420a) {
                        boolean unused = b.f7420a = false;
                        a.this.d();
                    }
                }
            };
        }

        public a(int i2) {
            this.f7423a = 30000;
            this.f7424b = new Runnable() { // from class: com.bytedance.frameworks.baselib.network.b.a.1
                @Override // java.lang.Runnable
                public void run() {
                    if (b.f7420a) {
                        boolean unused = b.f7420a = false;
                        a.this.d();
                    }
                }
            };
            this.f7423a = i2;
        }

        public Runnable a() {
            return this.f7424b;
        }

        public int b() {
            return this.f7423a;
        }

        public void a(int i2) {
            this.f7423a = i2;
        }
    }
}
