package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
/* compiled from: DynamicScreenUtil.java */
/* loaded from: classes10.dex */
public class ag {
    public static int[] a(Context context) {
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

    public static int a(Context context, int i2) {
        if (context != null) {
            return (int) ((i2 / context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        return 0;
    }
}
