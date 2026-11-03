package com.oasis.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.WindowManager;
/* loaded from: classes9.dex */
public final class NotchUtils {

    /* renamed from: a  reason: collision with root package name */
    private static e f17643a;

    /* renamed from: b  reason: collision with root package name */
    private static Context f17644b;

    private static e a() {
        e eVar = f17643a;
        if (eVar != null) {
            return eVar;
        }
        String str = Build.BRAND;
        f17643a = str.equalsIgnoreCase("xiaomi") ? new i() : (str.equalsIgnoreCase("HONOR") || str.equalsIgnoreCase("HUAWEI")) ? new b() : str.equalsIgnoreCase(com.bytedance.common.utility.e.ROM_OPPO) ? new f() : str.equalsIgnoreCase("vivo") ? new h() : str.equalsIgnoreCase("Meizu") ? new d() : str.equalsIgnoreCase("Lenovo") ? new c() : new a();
        return f17643a;
    }

    public static int[] getSafeAreaPadding() {
        if (Build.VERSION.SDK_INT < 28) {
            return a().b(f17644b);
        }
        DisplayCutout displayCutout = ((Activity) f17644b).getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new int[]{displayCutout.getSafeInsetLeft(), displayCutout.getSafeInsetTop(), displayCutout.getSafeInsetRight(), displayCutout.getSafeInsetBottom()};
    }

    public static int getScreenLayout() {
        return f17644b.getResources().getConfiguration().screenLayout & 15;
    }

    public static int[] getScreenSize() {
        WindowManager windowManager = (WindowManager) f17644b.getSystemService("window");
        if (windowManager == null) {
            return null;
        }
        return g.a(windowManager.getDefaultDisplay());
    }

    public static boolean hasNotch(Context context) {
        f17644b = context;
        return a().a(f17644b);
    }

    public static boolean isFoldDisplay() {
        return a().b();
    }

    public static boolean supportUniformAPI() {
        return a().a();
    }
}
