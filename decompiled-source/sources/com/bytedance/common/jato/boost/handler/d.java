package com.bytedance.common.jato.boost.handler;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.View;
/* compiled from: SimpleToStringHandlerOpt.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4581a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4582b = 2;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4583c = 4;

    /* renamed from: d  reason: collision with root package name */
    public static final int f4584d = 8;

    /* renamed from: e  reason: collision with root package name */
    private static int f4585e = -1;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f4586f = false;

    /* renamed from: g  reason: collision with root package name */
    private static boolean f4587g = false;

    public static void a(int i2) {
        if (f4585e < 0) {
            f4585e = i2;
            try {
                c();
                f4586f = true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void c() {
        if ((f4585e & 1) == 1) {
            a.a();
        }
        if ((f4585e & 2) == 2) {
            e.a();
        }
        if ((f4585e & 4) == 4) {
            b.a();
        }
        if ((f4585e & 8) == 8) {
            f4587g = true;
        }
    }

    public static void a(Application application) {
        if (f4586f && (f4585e & 7) != 0) {
            b(application);
        }
    }

    private static void b(Application application) {
        if (application == null) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.bytedance.common.jato.boost.handler.d.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                if ((d.f4585e & 1) == 1) {
                    a.a(activity);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(final Activity activity) {
                View decorView = activity.getWindow().getDecorView();
                if (decorView == null || (d.f4585e & 2) != 2) {
                    return;
                }
                decorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.bytedance.common.jato.boost.handler.d.1.1
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view) {
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view) {
                        e.a(activity.getWindow().getDecorView());
                    }
                });
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                if ((d.f4585e & 4) == 4) {
                    b.a(Choreographer.getInstance());
                }
            }
        });
    }

    public static boolean a() {
        return f4587g;
    }
}
