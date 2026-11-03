package com.bytedance.apm6.foundation;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.bytedance.apm6.service.lifecycle.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
/* compiled from: ActivityLifeManager.java */
/* loaded from: classes.dex */
public class a implements Application.ActivityLifecycleCallbacks, com.bytedance.apm6.service.lifecycle.a {

    /* renamed from: b  reason: collision with root package name */
    private WeakReference<Activity> f2189b;

    /* renamed from: d  reason: collision with root package name */
    private boolean f2191d;

    /* renamed from: e  reason: collision with root package name */
    private int f2192e;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f2193f;

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<c> f2188a = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    private String f2190c = null;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public a() {
        Application context = com.bytedance.apm6.foundation.context.a.getContext();
        context.unregisterActivityLifecycleCallbacks(this);
        context.registerActivityLifecycleCallbacks(this);
    }

    @Override // com.bytedance.apm6.service.lifecycle.a
    public boolean isForeground() {
        return this.f2193f;
    }

    @Override // com.bytedance.apm6.service.lifecycle.a
    public void register(c cVar) {
        if (cVar == null) {
            return;
        }
        synchronized (this.f2188a) {
            this.f2188a.add(cVar);
        }
    }

    @Override // com.bytedance.apm6.service.lifecycle.a
    public void unregister(c cVar) {
        if (cVar == null) {
            return;
        }
        synchronized (this.f2188a) {
            this.f2188a.remove(cVar);
        }
    }

    @Override // com.bytedance.apm6.service.lifecycle.a
    public String getTopActivityClassName() {
        WeakReference<Activity> weakReference = this.f2189b;
        String str = "";
        if (weakReference == null) {
            return "";
        }
        Activity activity = weakReference.get();
        return (activity == null || (str = this.f2190c) != null) ? str : activity.getClass().getCanonicalName();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.f2189b = new WeakReference<>(activity);
        this.f2190c = null;
        for (Object obj : a()) {
            ((c) obj).onActivityResume(activity);
        }
    }

    private void a(Activity activity) {
        for (Object obj : a()) {
            ((c) obj).onFront(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        for (Object obj : a()) {
            ((c) obj).onActivityPause(activity);
        }
    }

    private void b(Activity activity) {
        for (Object obj : a()) {
            ((c) obj).onBackground(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        for (Object obj : a()) {
            ((c) obj).onActivityCreated(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        for (Object obj : a()) {
            ((c) obj).onActivityStarted(activity);
        }
        if (this.f2191d) {
            this.f2191d = false;
            return;
        }
        int i2 = this.f2192e + 1;
        this.f2192e = i2;
        if (i2 == 1) {
            this.f2193f = true;
            a(activity);
        }
    }

    private Object[] a() {
        Object[] array;
        synchronized (this.f2188a) {
            array = this.f2188a.size() > 0 ? this.f2188a.toArray() : null;
        }
        return array == null ? new Object[0] : array;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity.isChangingConfigurations()) {
            this.f2191d = true;
            return;
        }
        int i2 = this.f2192e - 1;
        this.f2192e = i2;
        if (i2 == 0) {
            this.f2193f = false;
            b(activity);
        }
    }
}
