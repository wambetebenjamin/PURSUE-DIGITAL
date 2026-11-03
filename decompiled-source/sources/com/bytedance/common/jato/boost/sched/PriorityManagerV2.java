package com.bytedance.common.jato.boost.sched;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Process;
import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class PriorityManagerV2 {

    /* renamed from: a  reason: collision with root package name */
    private static volatile int f4627a;

    /* renamed from: b  reason: collision with root package name */
    private static volatile Application.ActivityLifecycleCallbacks f4628b;

    private static native void fetchRenderThreadTid();

    private static native int getRenderThreadTid();

    private static native int nativeResetPriority(int i2);

    private static native int nativeSetPriority(int i2, int i3);

    static /* synthetic */ int d() {
        return getRenderThreadTid();
    }

    static {
        d.loadLibrary();
        f4627a = -1;
        f4628b = null;
    }

    public static void a(boolean z, boolean z2) {
        fetchRenderThreadTid();
    }

    public static int a() {
        if (f4627a < 0) {
            f4627a = getRenderThreadTid();
        }
        return f4627a;
    }

    public static void a(int i2) {
        a(Process.myTid(), i2);
    }

    public static void a(int i2, int i3) {
        nativeSetPriority(i2, i3);
    }

    public static void b() {
        b(Process.myTid());
    }

    public static void b(int i2) {
        nativeResetPriority(i2);
    }

    public static synchronized void a(final Application application, final int i2) {
        synchronized (PriorityManagerV2.class) {
            if (f4627a < 0) {
                f4627a = getRenderThreadTid();
            }
            if (f4627a > 0) {
                a(f4627a, i2);
            } else if (f4628b == null) {
                f4628b = new Application.ActivityLifecycleCallbacks() { // from class: com.bytedance.common.jato.boost.sched.PriorityManagerV2.1
                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityDestroyed(Activity activity) {
                    }

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
                    public void onActivityStarted(Activity activity) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStopped(Activity activity) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityCreated(Activity activity, Bundle bundle) {
                        int unused = PriorityManagerV2.f4627a = PriorityManagerV2.d();
                        if (PriorityManagerV2.f4627a > 0) {
                            PriorityManagerV2.a(PriorityManagerV2.f4627a, i2);
                            application.unregisterActivityLifecycleCallbacks(this);
                        }
                    }
                };
                application.registerActivityLifecycleCallbacks(f4628b);
            }
        }
    }

    public static synchronized void c() {
        synchronized (PriorityManagerV2.class) {
            if (f4627a > 0) {
                b(f4627a);
            }
        }
    }
}
