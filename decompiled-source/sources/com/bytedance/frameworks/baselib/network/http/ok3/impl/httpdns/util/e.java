package com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.brentvatne.react.ReactVideoViewManager;
import com.bytedance.common.utility.collection.WeakHandler;
import java.lang.reflect.Field;
import java.util.Map;
/* compiled from: LifeCycleMonitor.java */
/* loaded from: classes2.dex */
public class e implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f8071a = false;

    /* renamed from: b  reason: collision with root package name */
    private static final int f8072b = 30000;

    /* renamed from: c  reason: collision with root package name */
    private static WeakHandler f8073c = new WeakHandler(Looper.getMainLooper(), new WeakHandler.IHandler() { // from class: com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.e.1
        @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
        public void handleMsg(Message message) {
        }
    });

    /* renamed from: d  reason: collision with root package name */
    private a f8074d;

    /* renamed from: e  reason: collision with root package name */
    private Runnable f8075e = new Runnable() { // from class: com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.e.2
        @Override // java.lang.Runnable
        public void run() {
            if (e.f8071a) {
                boolean unused = e.f8071a = false;
                if (e.this.f8074d != null) {
                    e.this.f8074d.b();
                }
            }
        }
    };

    /* compiled from: LifeCycleMonitor.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a();

        void b();
    }

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

    public e() {
        if (b() != null) {
            f8071a = true;
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (!f8071a) {
            f8071a = true;
            a aVar = this.f8074d;
            if (aVar != null) {
                aVar.a();
            }
        }
        f8073c.removeCallbacks(this.f8075e);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (f8071a) {
            f8073c.postDelayed(this.f8075e, 30000L);
        }
    }

    public void a(a aVar) {
        this.f8074d = aVar;
    }
}
