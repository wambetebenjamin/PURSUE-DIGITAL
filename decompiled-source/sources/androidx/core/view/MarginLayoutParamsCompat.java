package androidx.core.view;

import android.os.Build;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public final class MarginLayoutParamsCompat {
    public static int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (Build.VERSION.SDK_INT >= 17) {
            return Api17Impl.getMarginStart(marginLayoutParams);
        }
        return marginLayoutParams.leftMargin;
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (Build.VERSION.SDK_INT >= 17) {
            return Api17Impl.getMarginEnd(marginLayoutParams);
        }
        return marginLayoutParams.rightMargin;
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.setMarginStart(marginLayoutParams, i2);
        } else {
            marginLayoutParams.leftMargin = i2;
        }
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.setMarginEnd(marginLayoutParams, i2);
        } else {
            marginLayoutParams.rightMargin = i2;
        }
    }

    public static boolean isMarginRelative(ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (Build.VERSION.SDK_INT >= 17) {
            return Api17Impl.isMarginRelative(marginLayoutParams);
        }
        return false;
    }

    public static int getLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams) {
        int layoutDirection = Build.VERSION.SDK_INT >= 17 ? Api17Impl.getLayoutDirection(marginLayoutParams) : 0;
        if (layoutDirection == 0 || layoutDirection == 1) {
            return layoutDirection;
        }
        return 0;
    }

    public static void setLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.setLayoutDirection(marginLayoutParams, i2);
        }
    }

    public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.resolveLayoutDirection(marginLayoutParams, i2);
        }
    }

    private MarginLayoutParamsCompat() {
    }

    /* loaded from: classes.dex */
    static class Api17Impl {
        private Api17Impl() {
        }

        static int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.getMarginStart();
        }

        static int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.getMarginEnd();
        }

        static void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
            marginLayoutParams.setMarginStart(i2);
        }

        static void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
            marginLayoutParams.setMarginEnd(i2);
        }

        static boolean isMarginRelative(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.isMarginRelative();
        }

        static int getLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.getLayoutDirection();
        }

        static void setLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
            marginLayoutParams.setLayoutDirection(i2);
        }

        static void resolveLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
            marginLayoutParams.resolveLayoutDirection(i2);
        }
    }
}
