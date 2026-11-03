package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import com.google.android.material.R;
/* loaded from: classes6.dex */
public class MaterialAttributes {
    public static TypedValue resolve(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static int resolveOrThrow(Context context, int i2, String str) {
        TypedValue resolve = resolve(context, i2);
        if (resolve == null) {
            throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i2)));
        }
        return resolve.data;
    }

    public static int resolveOrThrow(View view, int i2) {
        return resolveOrThrow(view.getContext(), i2, view.getClass().getCanonicalName());
    }

    public static boolean resolveBooleanOrThrow(Context context, int i2, String str) {
        return resolveOrThrow(context, i2, str) != 0;
    }

    public static boolean resolveBoolean(Context context, int i2, boolean z) {
        TypedValue resolve = resolve(context, i2);
        return (resolve == null || resolve.type != 18) ? z : resolve.data != 0;
    }

    public static int resolveInteger(Context context, int i2, int i3) {
        TypedValue resolve = resolve(context, i2);
        return (resolve == null || resolve.type != 16) ? i3 : resolve.data;
    }

    public static int resolveMinimumAccessibleTouchTarget(Context context) {
        return resolveDimension(context, R.attr.minTouchTargetSize, R.dimen.mtrl_min_touch_target_size);
    }

    public static int resolveDimension(Context context, int i2, int i3) {
        float dimension;
        TypedValue resolve = resolve(context, i2);
        if (resolve == null || resolve.type != 5) {
            dimension = context.getResources().getDimension(i3);
        } else {
            dimension = resolve.getDimension(context.getResources().getDisplayMetrics());
        }
        return (int) dimension;
    }
}
