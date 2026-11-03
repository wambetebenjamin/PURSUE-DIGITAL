package com.bytedance.sdk.account.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: ActivityStack.java */
/* loaded from: classes2.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private static int f13097a = 0;

    /* renamed from: c  reason: collision with root package name */
    private static final String f13099c = "AccountActivityStack";

    /* renamed from: b  reason: collision with root package name */
    private static final LinkedList<Activity> f13098b = new LinkedList<>();

    /* renamed from: d  reason: collision with root package name */
    private static final HashSet<a> f13100d = new HashSet<>();

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicBoolean f13101e = new AtomicBoolean(false);

    /* compiled from: ActivityStack.java */
    /* loaded from: classes2.dex */
    public static class a {
        public void a() {
        }

        public void b() {
        }
    }

    static /* synthetic */ int e() {
        int i2 = f13097a;
        f13097a = i2 + 1;
        return i2;
    }

    static /* synthetic */ int f() {
        int i2 = f13097a;
        f13097a = i2 - 1;
        return i2;
    }

    public static void a(Application application) {
        if (f13101e.compareAndSet(false, true)) {
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.bytedance.sdk.account.utils.n.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    n.f13098b.add(activity);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    n.e();
                    if (n.f13097a == 1) {
                        n.j();
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    n.f();
                    if (n.f13097a == 0) {
                        n.k();
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    n.f13098b.remove(activity);
                }
            });
        }
    }

    public static boolean a() {
        return f13097a > 0;
    }

    public static void a(a aVar) {
        if (aVar != null) {
            f13100d.add(aVar);
        }
    }

    public static void b(a aVar) {
        if (aVar != null) {
            f13100d.remove(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j() {
        Log.d(f13099c, "notifyForeground");
        Iterator<a> it = f13100d.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void k() {
        Log.d(f13099c, "notifyForeground");
        Iterator<a> it = f13100d.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
    }

    public static Activity a(Activity activity) {
        LinkedList<Activity> linkedList = f13098b;
        boolean z = false;
        for (int size = linkedList.size() - 1; size >= 0; size--) {
            if (z) {
                Activity activity2 = linkedList.get(size);
                if (activity2 != null && !activity2.isFinishing()) {
                    return activity2;
                }
            } else if (linkedList.get(size) == activity) {
                z = true;
            }
        }
        return null;
    }

    public static Activity b() {
        LinkedList<Activity> linkedList = f13098b;
        if (linkedList.isEmpty()) {
            return null;
        }
        return linkedList.getLast();
    }

    public static synchronized Activity[] c() {
        Activity[] activityArr;
        synchronized (n.class) {
            LinkedList<Activity> linkedList = f13098b;
            activityArr = (Activity[]) linkedList.toArray(new Activity[linkedList.size()]);
        }
        return activityArr;
    }
}
