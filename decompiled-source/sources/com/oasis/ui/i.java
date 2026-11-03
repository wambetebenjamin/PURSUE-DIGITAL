package com.oasis.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
/* loaded from: classes9.dex */
final class i implements e {
    @Override // com.oasis.ui.e
    public final boolean a() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @Override // com.oasis.ui.e
    public final boolean a(Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            Object invoke = loadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(loadClass, "ro.miui.notch", 0);
            if (invoke == null) {
                return false;
            }
            return ((Integer) invoke).intValue() == 1;
        } catch (Exception e2) {
            Log.e("XiaomiNotch", "hasNotch throw exception.", e2);
            return false;
        }
    }

    @Override // com.oasis.ui.e
    public final boolean b() {
        int i2;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            i2 = ((Integer) cls.getDeclaredMethod("getInt", String.class, Integer.TYPE).invoke(cls, "persist.sys.muiltdisplay_type", 0)).intValue();
        } catch (Exception e2) {
            Log.e("Oasis.UI", "[XiaomiNotch]Get foldType failed.", e2);
            i2 = 0;
        }
        return i2 == 2;
    }

    @Override // com.oasis.ui.e
    public final int[] b(Context context) {
        String str = Build.MODEL;
        if (!str.contains("PLAY") && !str.contains("Redmi 6 Pro")) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("notch_height", "dimen", "android");
            if (identifier > 0) {
                return g.a(resources.getDimensionPixelSize(identifier));
            }
            return null;
        }
        int[] a2 = g.a(((WindowManager) context.getSystemService("window")).getDefaultDisplay());
        Rect b2 = g.b(context);
        if (b2.width() + b2.height() < a2[0] + a2[1]) {
            return null;
        }
        int a3 = g.a(context);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return null;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        int[] a4 = g.a(defaultDisplay);
        int i2 = a4[0];
        int i3 = a4[1];
        int rotation = defaultDisplay.getRotation();
        return rotation != 0 ? rotation != 1 ? rotation != 2 ? rotation != 3 ? new int[]{0, 0, i2, i3} : new int[]{0, 0, i2 - a3, i3} : new int[]{0, 0, i2, i3 - a3} : new int[]{a3, 0, i2 - a3, i3} : new int[]{0, a3, i2, i3 - a3};
    }
}
