package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
/* loaded from: classes.dex */
public final class GravityCompat {
    public static final int END = 8388613;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;

    public static void apply(int i2, int i3, int i4, Rect rect, Rect rect2, int i5) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.apply(i2, i3, i4, rect, rect2, i5);
        } else {
            Gravity.apply(i2, i3, i4, rect, rect2);
        }
    }

    public static void apply(int i2, int i3, int i4, Rect rect, int i5, int i6, Rect rect2, int i7) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.apply(i2, i3, i4, rect, i5, i6, rect2, i7);
        } else {
            Gravity.apply(i2, i3, i4, rect, i5, i6, rect2);
        }
    }

    public static void applyDisplay(int i2, Rect rect, Rect rect2, int i3) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.applyDisplay(i2, rect, rect2, i3);
        } else {
            Gravity.applyDisplay(i2, rect, rect2);
        }
    }

    public static int getAbsoluteGravity(int i2, int i3) {
        return Build.VERSION.SDK_INT >= 17 ? Gravity.getAbsoluteGravity(i2, i3) : i2 & (-8388609);
    }

    private GravityCompat() {
    }

    /* loaded from: classes.dex */
    static class Api17Impl {
        private Api17Impl() {
        }

        static void apply(int i2, int i3, int i4, Rect rect, Rect rect2, int i5) {
            Gravity.apply(i2, i3, i4, rect, rect2, i5);
        }

        static void apply(int i2, int i3, int i4, Rect rect, int i5, int i6, Rect rect2, int i7) {
            Gravity.apply(i2, i3, i4, rect, i5, i6, rect2, i7);
        }

        static void applyDisplay(int i2, Rect rect, Rect rect2, int i3) {
            Gravity.applyDisplay(i2, rect, rect2, i3);
        }
    }
}
