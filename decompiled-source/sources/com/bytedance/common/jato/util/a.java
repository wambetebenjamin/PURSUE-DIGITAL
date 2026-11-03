package com.bytedance.common.jato.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
/* compiled from: ActivityLifeManager.java */
/* loaded from: classes2.dex */
public class a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<d> f5040a;

    /* renamed from: b  reason: collision with root package name */
    private WeakReference<Activity> f5041b;

    /* renamed from: c  reason: collision with root package name */
    private String f5042c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f5043d;

    /* renamed from: e  reason: collision with root package name */
    private int f5044e;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f5045f;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    /* compiled from: ActivityLifeManager.java */
    /* renamed from: com.bytedance.common.jato.util.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static final class C0067a {

        /* renamed from: a  reason: collision with root package name */
        private static final a f5046a = new a();

        private C0067a() {
        }
    }

    public static a getInstance() {
        return C0067a.f5046a;
    }

    private a() {
        this.f5040a = new ArrayList<>();
        this.f5042c = null;
    }

    public void init(Application application) {
        application.unregisterActivityLifecycleCallbacks(this);
        application.registerActivityLifecycleCallbacks(this);
    }

    public boolean isForeground() {
        return this.f5045f;
    }

    public void register(d dVar) {
        if (dVar == null) {
            return;
        }
        synchronized (this.f5040a) {
            this.f5040a.add(dVar);
        }
    }

    public void unregister(d dVar) {
        if (dVar == null) {
            return;
        }
        synchronized (this.f5040a) {
            this.f5040a.remove(dVar);
        }
    }

    public String getTopActivityClassName() {
        WeakReference<Activity> weakReference = this.f5041b;
        String str = "";
        if (weakReference == null) {
            return "";
        }
        Activity activity = weakReference.get();
        return (activity == null || (str = this.f5042c) != null) ? str : activity.getClass().getCanonicalName();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.f5041b = new WeakReference<>(activity);
        this.f5042c = null;
        for (Object obj : a()) {
            ((d) obj).onActivityResume(activity);
        }
    }

    private void a(Activity activity) {
        for (Object obj : a()) {
            ((d) obj).onFront(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        for (Object obj : a()) {
            ((d) obj).onActivityPause(activity);
        }
    }

    private void b(Activity activity) {
        for (Object obj : a()) {
            ((d) obj).onBackground(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        for (Object obj : a()) {
            ((d) obj).onActivityCreated(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        for (Object obj : a()) {
            ((d) obj).onActivityStarted(activity);
        }
        if (this.f5043d) {
            this.f5043d = false;
            return;
        }
        int i2 = this.f5044e + 1;
        this.f5044e = i2;
        if (i2 == 1) {
            this.f5045f = true;
            a(activity);
        }
    }

    private Object[] a() {
        Object[] array;
        synchronized (this.f5040a) {
            array = this.f5040a.size() > 0 ? this.f5040a.toArray() : null;
        }
        return array == null ? new Object[0] : array;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity.isChangingConfigurations()) {
            this.f5043d = true;
            return;
        }
        int i2 = this.f5044e - 1;
        this.f5044e = i2;
        if (i2 == 0) {
            this.f5045f = false;
            b(activity);
        }
    }
}
