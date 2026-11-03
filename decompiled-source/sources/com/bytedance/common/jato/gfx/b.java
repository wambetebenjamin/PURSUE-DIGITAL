package com.bytedance.common.jato.gfx;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.view.WindowCallbackWrapper;
/* compiled from: DrawController.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4733a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4734b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4735c = 2;

    /* renamed from: d  reason: collision with root package name */
    public static final int f4736d = 3;

    /* renamed from: e  reason: collision with root package name */
    public static final int f4737e = 0;

    /* renamed from: f  reason: collision with root package name */
    public static final int f4738f = 1;

    /* renamed from: g  reason: collision with root package name */
    public static final int f4739g = 2;

    /* renamed from: h  reason: collision with root package name */
    public static final int f4740h = 16666666;

    /* renamed from: i  reason: collision with root package name */
    public static final int f4741i = 8333333;

    /* renamed from: j  reason: collision with root package name */
    private static final String f4742j = "DrawController";
    private static int k = 0;
    private static int l = 0;
    private static int m = 1;
    private static long n = 0;
    private static int o = 3000;
    private static int p = -1;
    private static boolean q = false;
    private static boolean r = false;
    private static d s;
    private static a t;
    private static c u;

    /* compiled from: DrawController.java */
    /* loaded from: classes.dex */
    public interface a {
        boolean a(Activity activity);
    }

    /* compiled from: DrawController.java */
    /* loaded from: classes.dex */
    public interface c {
        void a();
    }

    /* compiled from: DrawController.java */
    /* loaded from: classes.dex */
    public interface d {
        boolean a();

        boolean b();
    }

    static /* synthetic */ int h() {
        int i2 = m;
        m = i2 + 1;
        return i2;
    }

    public static synchronized void a(Context context) {
        synchronized (b.class) {
            if (r) {
                return;
            }
            ((Application) context).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.bytedance.common.jato.gfx.b.1
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
                    if (b.t == null || !b.t.a(activity)) {
                        return;
                    }
                    Window.Callback callback = activity.getWindow().getCallback();
                    if (callback != null) {
                        activity.getWindow().setCallback(new C0060b(callback));
                    }
                    activity.getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new e());
                }
            });
            r = true;
        }
    }

    public static void a(int i2) {
        o = i2;
    }

    public static void a(int i2, int i3) {
        k = i2;
        l = i3;
        m = 1;
    }

    public static void a(d dVar) {
        s = dVar;
    }

    public static d a() {
        return s;
    }

    public static void a(a aVar) {
        t = aVar;
    }

    public static void a(c cVar) {
        u = cVar;
    }

    public static c b() {
        return u;
    }

    public static void a(Context context, float f2) {
        float refreshRate = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
        if (refreshRate == 0.0f) {
            refreshRate = 60.0f;
        }
        int i2 = (int) (1.0E9f / refreshRate);
        p = i2;
        if (i2 > 16666666) {
            p = f4740h;
        }
        if (p < 8333333) {
            p = f4741i;
        }
        int i3 = (int) (p * f2);
        p = i3;
        if (i3 < 4000000) {
            p = 4000000;
        }
    }

    /* compiled from: DrawController.java */
    /* loaded from: classes.dex */
    public static class e implements ViewTreeObserver.OnPreDrawListener {
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (b.p != -1) {
                if (b.q) {
                    boolean unused = b.q = false;
                    return false;
                }
                long c2 = com.bytedance.common.jato.gfx.a.c();
                if (c2 != 0 && System.nanoTime() - c2 > b.p) {
                    boolean unused2 = b.q = true;
                }
            }
            if (b.s == null || !b.s.a()) {
                int i2 = b.k;
                if (i2 != 1) {
                    if (i2 != 2) {
                        return true;
                    }
                    b.h();
                    int i3 = b.l;
                    return i3 != 0 ? i3 != 1 ? (i3 == 2 && b.m % 3 == 0) ? false : true : b.m % 4 != 0 : b.m % 5 != 0;
                }
                return false;
            }
            return b.s.b();
        }
    }

    /* compiled from: DrawController.java */
    /* renamed from: com.bytedance.common.jato.gfx.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0060b extends WindowCallbackWrapper {
        public C0060b(Window.Callback callback) {
            super(callback);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - b.n > b.o) {
                long unused = b.n = currentTimeMillis;
                if (motionEvent.getAction() == 0 && b.u != null) {
                    b.u.a();
                }
            }
            return super.dispatchTouchEvent(motionEvent);
        }
    }
}
