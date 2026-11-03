package com.bytedance.common.push;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Message;
import com.bytedance.common.push.notification.BaseBannerActivity;
import com.bytedance.common.utility.collection.WeakHandler;
import com.bytedance.push.utils.h;
import com.bytedance.push.utils.p;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
/* compiled from: ActivityLifecycleObserver.java */
/* loaded from: classes2.dex */
public class b extends Observable implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    private static final p<b> f5125a = new p<b>() { // from class: com.bytedance.common.push.b.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.push.utils.p
        /* renamed from: a */
        public b b(Object... objArr) {
            return new b();
        }
    };

    /* renamed from: f  reason: collision with root package name */
    private static volatile boolean f5126f = true;

    /* renamed from: b  reason: collision with root package name */
    private WeakReference<Activity> f5127b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f5128c;

    /* renamed from: d  reason: collision with root package name */
    private final Runnable f5129d;

    /* renamed from: e  reason: collision with root package name */
    private int f5130e;

    /* renamed from: g  reason: collision with root package name */
    private final WeakHandler f5131g;

    /* renamed from: h  reason: collision with root package name */
    private final List<Application.ActivityLifecycleCallbacks> f5132h;

    public static b getIns() {
        return f5125a.get(new Object[0]);
    }

    private b() {
        this.f5129d = new Runnable() { // from class: com.bytedance.common.push.b.2
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.f5128c) {
                    b.this.f5128c = false;
                    h.d("ActivityLifecycleObserver", "sAppAlive = false");
                }
            }
        };
        this.f5131g = new WeakHandler(new WeakHandler.IHandler() { // from class: com.bytedance.common.push.b.3
            @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
            public void handleMsg(Message message) {
            }
        });
        this.f5132h = new ArrayList();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Object[] c2 = c();
        if (c2 != null) {
            for (Object obj : c2) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityCreated(activity, bundle);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (h.debug()) {
            h.d("ActivityLifecycleObserver", "onActivityStarted:" + activity.getClass().getName());
        }
        Object[] c2 = c();
        if (c2 != null) {
            for (Object obj : c2) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityStarted(activity);
            }
        }
        if (activity instanceof BaseBannerActivity) {
            h.d("ActivityLifecycleObserver", "on banner ActivityStarted,ignore");
            return;
        }
        this.f5127b = new WeakReference<>(activity);
        if (this.f5130e < 0) {
            this.f5130e = 0;
        }
        if (this.f5130e == 0) {
            b();
        }
        this.f5130e++;
        if (!this.f5128c) {
            this.f5128c = true;
            h.d("ActivityLifecycleObserver", "onResume sAppAlive = true");
        }
        this.f5131g.removeCallbacks(this.f5129d);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        h.d("ActivityLifecycleObserver", "onActivityResumed:" + activity.getClass().getName());
        Object[] c2 = c();
        if (c2 != null) {
            for (Object obj : c2) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityResumed(activity);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (h.debug()) {
            h.d("ActivityLifecycleObserver", "onActivityPaused:" + activity.getClass().getName());
        }
        Object[] c2 = c();
        if (c2 != null) {
            for (Object obj : c2) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityPaused(activity);
            }
        }
    }

    private void a() {
        h.d("ActivityLifecycleObserver", "onEnterBackground");
        f5126f = true;
        com.bytedance.push.appstatus.a.getIns().onEnterBackground();
        setChanged();
        notifyObservers(Boolean.valueOf(f5126f));
    }

    private void b() {
        h.d("ActivityLifecycleObserver", "onEnterForeground");
        f5126f = false;
        com.bytedance.push.appstatus.a.getIns().onEnterForeground();
        setChanged();
        notifyObservers(Boolean.valueOf(f5126f));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        h.d("ActivityLifecycleObserver", "onActivityStopped:" + activity.getClass().getName());
        if (this.f5128c) {
            this.f5131g.postDelayed(this.f5129d, 30000L);
        }
        Object[] c2 = c();
        if (c2 != null) {
            for (Object obj : c2) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityStopped(activity);
            }
        }
        int i2 = this.f5130e - 1;
        this.f5130e = i2;
        if (i2 <= 0) {
            a();
        }
        if (this.f5130e < 0) {
            this.f5130e = 0;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Object[] c2 = c();
        if (c2 != null) {
            for (Object obj : c2) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivitySaveInstanceState(activity, bundle);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Object[] c2 = c();
        if (c2 != null) {
            for (Object obj : c2) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityDestroyed(activity);
            }
        }
    }

    public boolean isBackGround() {
        return f5126f;
    }

    public boolean isApplicationActive() {
        return this.f5128c;
    }

    public void addActivityLifeCycleListener(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (activityLifecycleCallbacks == null) {
            return;
        }
        synchronized (this.f5132h) {
            if (this.f5132h.contains(activityLifecycleCallbacks)) {
                return;
            }
            this.f5132h.add(activityLifecycleCallbacks);
        }
    }

    public void removeActivityLifeCycleListener(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (activityLifecycleCallbacks == null) {
            return;
        }
        synchronized (this.f5132h) {
            this.f5132h.remove(activityLifecycleCallbacks);
        }
    }

    private Object[] c() {
        Object[] array;
        synchronized (this.f5132h) {
            array = this.f5132h.size() > 0 ? this.f5132h.toArray() : null;
        }
        return array;
    }

    public Activity getTopActivity() {
        WeakReference<Activity> weakReference = this.f5127b;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
