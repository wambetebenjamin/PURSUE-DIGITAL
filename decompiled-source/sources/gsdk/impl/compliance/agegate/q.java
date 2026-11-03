package gsdk.impl.compliance.agegate;

import android.graphics.Color;
/* compiled from: ColorUtil.java */
/* loaded from: classes10.dex */
public class q {
    public static int a(int i2, int i3, float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return Color.argb(Math.round(Color.alpha(i2) + ((Color.alpha(i3) - Color.alpha(i2)) * f2)), Math.round(Color.red(i2) + ((Color.red(i3) - Color.red(i2)) * f2)), Math.round(Color.green(i2) + ((Color.green(i3) - Color.green(i2)) * f2)), Math.round(Color.blue(i2) + ((Color.blue(i3) - Color.blue(i2)) * f2)));
    }
}
