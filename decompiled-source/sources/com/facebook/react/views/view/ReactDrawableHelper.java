package com.facebook.react.views.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.uimanager.ViewProps;
/* loaded from: classes8.dex */
public class ReactDrawableHelper {
    private static final TypedValue sResolveOutValue = new TypedValue();

    public static Drawable createDrawableFromJSDescription(Context context, ReadableMap readableMap) {
        int color;
        String string = readableMap.getString("type");
        if ("ThemeAttrAndroid".equals(string)) {
            String string2 = readableMap.getString("attribute");
            SoftAssertions.assertNotNull(string2);
            int identifier = context.getResources().getIdentifier(string2, "attr", "android");
            if (identifier == 0) {
                throw new JSApplicationIllegalArgumentException("Attribute " + string2 + " couldn't be found in the resource list");
            }
            Resources.Theme theme = context.getTheme();
            TypedValue typedValue = sResolveOutValue;
            if (theme.resolveAttribute(identifier, typedValue, true)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    return context.getResources().getDrawable(typedValue.resourceId, context.getTheme());
                }
                return context.getResources().getDrawable(typedValue.resourceId);
            }
            throw new JSApplicationIllegalArgumentException("Attribute " + string2 + " couldn't be resolved into a drawable");
        } else if ("RippleAndroid".equals(string)) {
            if (Build.VERSION.SDK_INT < 21) {
                throw new JSApplicationIllegalArgumentException("Ripple drawable is not available on android API <21");
            }
            if (readableMap.hasKey(ViewProps.COLOR) && !readableMap.isNull(ViewProps.COLOR)) {
                color = readableMap.getInt(ViewProps.COLOR);
            } else {
                Resources.Theme theme2 = context.getTheme();
                TypedValue typedValue2 = sResolveOutValue;
                if (theme2.resolveAttribute(16843820, typedValue2, true)) {
                    color = context.getResources().getColor(typedValue2.resourceId);
                } else {
                    throw new JSApplicationIllegalArgumentException("Attribute colorControlHighlight couldn't be resolved into a drawable");
                }
            }
            return new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{color}), null, (readableMap.hasKey("borderless") && !readableMap.isNull("borderless") && readableMap.getBoolean("borderless")) ? null : new ColorDrawable(-1));
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid type for android drawable: " + string);
        }
    }
}
