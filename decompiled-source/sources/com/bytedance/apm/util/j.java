package com.bytedance.apm.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;
import com.bytedance.apm.core.ActivityLifeObserver;
import com.bytedance.services.apm.api.IActivityLifeObserver;
/* compiled from: FpsUtil.java */
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1700a = 60;

    /* renamed from: b  reason: collision with root package name */
    private static final int f1701b = 90;

    /* renamed from: c  reason: collision with root package name */
    private static final int f1702c = 120;

    /* renamed from: d  reason: collision with root package name */
    private static final int f1703d = 60;

    /* renamed from: e  reason: collision with root package name */
    private static final float f1704e = 1000.0f;

    /* renamed from: f  reason: collision with root package name */
    private static final float f1705f = 5.1f;

    /* renamed from: g  reason: collision with root package name */
    private static float f1706g;

    /* renamed from: h  reason: collision with root package name */
    private static int f1707h;

    /* renamed from: i  reason: collision with root package name */
    private static int f1708i;

    /* renamed from: j  reason: collision with root package name */
    private static boolean f1709j;
    private static int k;
    private static int l;

    static {
        a();
    }

    private static void a() {
        Context context = com.bytedance.apm.c.getContext();
        ActivityLifeObserver.getInstance().register(new IActivityLifeObserver() { // from class: com.bytedance.apm.util.j.1
            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onActivityPause(Activity activity) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onActivityResume(Activity activity) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onActivityStarted(Activity activity) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onBackground(Activity activity) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onChange(Activity activity, Fragment fragment) {
            }

            @Override // com.bytedance.services.apm.api.IActivityLifeObserver
            public void onFront(Activity activity) {
                j.b(activity);
            }
        });
        float f2 = 0.0f;
        if (!com.bytedance.apm.c.supportMultiFrameRate() || Build.VERSION.SDK_INT < 23 || context == null) {
            f1708i = 60;
            f1707h = 60;
            f1709j = true;
            f1706g = f1704e / 60;
            l = 60;
            k = 60;
            if (Build.VERSION.SDK_INT < 23 || context == null) {
                return;
            }
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            k = a(defaultDisplay.getRefreshRate());
            for (Display.Mode mode : defaultDisplay.getSupportedModes()) {
                float refreshRate = mode.getRefreshRate();
                if (refreshRate > f2) {
                    f2 = refreshRate;
                }
            }
            l = a(f2);
            return;
        }
        Display defaultDisplay2 = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        float refreshRate2 = defaultDisplay2.getRefreshRate();
        for (Display.Mode mode2 : defaultDisplay2.getSupportedModes()) {
            float refreshRate3 = mode2.getRefreshRate();
            if (refreshRate3 > f2) {
                f2 = refreshRate3;
            }
        }
        int a2 = a(refreshRate2);
        f1707h = a2;
        k = a2;
        int a3 = a(f2);
        f1708i = a3;
        int i2 = f1707h;
        f1709j = i2 == a3;
        f1706g = f1704e / i2;
    }

    private static int a(float f2) {
        if (Math.abs(f2 - 60.0f) < f1705f) {
            return 60;
        }
        if (Math.abs(f2 - 90.0f) < f1705f) {
            return 90;
        }
        if (Math.abs(f2 - 120.0f) < f1705f) {
            return 120;
        }
        return (int) f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        float refreshRate = activity.getWindowManager().getDefaultDisplay().getRefreshRate();
        k = a(refreshRate);
        if (com.bytedance.apm.c.supportMultiFrameRate()) {
            int a2 = a(refreshRate);
            f1707h = a2;
            f1709j = a2 == f1708i;
            f1706g = f1704e / a2;
        }
    }

    public static float getFrameIntervalMillis() {
        return f1706g;
    }

    public static int getRefreshRate() {
        return f1707h;
    }

    public static int getDeviceMaxRefreshRate() {
        return f1708i;
    }

    public static boolean isUsingMaxRefreshRate() {
        return f1709j;
    }

    public static int b() {
        return k;
    }

    public static int c() {
        return l;
    }
}
