package com.oasis.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
/* loaded from: classes9.dex */
final class g {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (Exception e2) {
            Log.e("Oasis.UI", "[ScreenUtils]Get system property failed.", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] a(int i2) {
        return new int[]{i2, 0, 0, 0};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] a(Display display) {
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(point);
        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            point = new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        int rotation = display.getRotation();
        return (rotation == 0 || rotation == 2) ? new int[]{point.y, point.x} : new int[]{point.x, point.y};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Rect b(Context context) {
        Rect rect = new Rect();
        ((Activity) context).getWindow().getDecorView().getLocalVisibleRect(rect);
        return rect;
    }
}
