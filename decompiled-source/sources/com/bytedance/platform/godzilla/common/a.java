package com.bytedance.platform.godzilla.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
/* compiled from: ActivityLifecycleUtil.java */
/* loaded from: classes2.dex */
public final class a {
    public static final int ACTIVITY_STATE_DESTORY = 3;
    public static final int ACTIVITY_STATE_PAUSE = 1;
    public static final int ACTIVITY_STATE_RESUME = 0;
    public static final int ACTIVITY_STATE_STOP = 2;

    /* renamed from: a  reason: collision with root package name */
    private static Map<Integer, WeakReference<Activity>> f11039a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private static WeakReference<Activity> f11040b = null;

    /* renamed from: c  reason: collision with root package name */
    private static WeakReference<Activity> f11041c = null;

    /* renamed from: d  reason: collision with root package name */
    private static WeakReference<Activity> f11042d = null;

    /* renamed from: e  reason: collision with root package name */
    private static WeakReference<Activity> f11043e = null;

    private a() {
    }

    public static void initActivityLifecycle(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.bytedance.platform.godzilla.common.a.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                a.f11039a.put(0, new WeakReference(activity));
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                a.f11039a.put(1, new WeakReference(activity));
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                a.f11039a.put(2, new WeakReference(activity));
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                a.f11039a.put(3, new WeakReference(activity));
            }
        });
    }

    public static Activity getActivity(int i2) {
        if (f11039a.get(Integer.valueOf(i2)) != null) {
            return f11039a.get(Integer.valueOf(i2)).get();
        }
        return null;
    }
}
