package com.google.android.material.color;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.resources.MaterialAttributes;
/* loaded from: classes8.dex */
public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;

    private MaterialColors() {
    }

    public static int getColor(View view, int i2) {
        return MaterialAttributes.resolveOrThrow(view, i2);
    }

    public static int getColor(Context context, int i2, String str) {
        return MaterialAttributes.resolveOrThrow(context, i2, str);
    }

    public static int getColor(View view, int i2, int i3) {
        return getColor(view.getContext(), i2, i3);
    }

    public static int getColor(Context context, int i2, int i3) {
        TypedValue resolve = MaterialAttributes.resolve(context, i2);
        return resolve != null ? resolve.data : i3;
    }

    public static int layer(View view, int i2, int i3) {
        return layer(view, i2, i3, 1.0f);
    }

    public static int layer(View view, int i2, int i3, float f2) {
        return layer(getColor(view, i2), getColor(view, i3), f2);
    }

    public static int layer(int i2, int i3, float f2) {
        return layer(i2, ColorUtils.setAlphaComponent(i3, Math.round(Color.alpha(i3) * f2)));
    }

    public static int layer(int i2, int i3) {
        return ColorUtils.compositeColors(i3, i2);
    }

    public static int compositeARGBWithAlpha(int i2, int i3) {
        return ColorUtils.setAlphaComponent(i2, (Color.alpha(i2) * i3) / 255);
    }

    public static boolean isColorLight(int i2) {
        return i2 != 0 && ColorUtils.calculateLuminance(i2) > 0.5d;
    }
}
