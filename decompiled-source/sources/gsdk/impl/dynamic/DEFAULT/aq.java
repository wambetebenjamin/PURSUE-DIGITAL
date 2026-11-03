package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
/* compiled from: ScreenUtil.java */
/* loaded from: classes10.dex */
public class aq {
    public static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static int[] b(Context context) {
        int[] iArr = new int[2];
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(defaultDisplay, point);
                i2 = point.x;
                i3 = point.y;
            } catch (Throwable unused) {
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
        return iArr;
    }

    public static int c(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static Rect a(Context context, int i2, int i3) {
        int i4;
        int i5;
        int[] b2 = b(context);
        int i6 = 0;
        int i7 = b2[0];
        int i8 = b2[1];
        if (a(context)) {
            int i9 = (i7 - i2) / 2;
            i4 = 0;
            i6 = i9;
            i3 = i2 + i9;
            i5 = i3;
        } else if (c(context) == 1) {
            int i10 = (i8 - i2) / 2;
            i5 = i2 + i10;
            i4 = i10;
        } else {
            i6 = i7 - i3;
            i4 = (i8 - i2) / 2;
            i5 = i4 + i2;
            i3 = i7;
        }
        return new Rect(i6, i4, i3, i5);
    }
}
