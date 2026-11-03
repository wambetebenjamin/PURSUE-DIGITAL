package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
/* loaded from: classes.dex */
public final class DrawableDecoderCompat {
    private static volatile boolean shouldCallAppCompatResources = true;

    private DrawableDecoderCompat() {
    }

    public static Drawable getDrawable(Context context, Context context2, int i2) {
        return getDrawable(context, context2, i2, null);
    }

    public static Drawable getDrawable(Context context, int i2, Resources.Theme theme) {
        return getDrawable(context, context, i2, theme);
    }

    private static Drawable getDrawable(Context context, Context context2, int i2, Resources.Theme theme) {
        try {
            if (shouldCallAppCompatResources) {
                return loadDrawableV7(context2, i2, theme);
            }
        } catch (Resources.NotFoundException unused) {
        } catch (IllegalStateException e2) {
            if (context.getPackageName().equals(context2.getPackageName())) {
                throw e2;
            }
            return ContextCompat.getDrawable(context2, i2);
        } catch (NoClassDefFoundError unused2) {
            shouldCallAppCompatResources = false;
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        return loadDrawableV4(context2, i2, theme);
    }

    private static Drawable loadDrawableV7(Context context, int i2, Resources.Theme theme) {
        if (theme != null) {
            context = new ContextThemeWrapper(context, theme);
        }
        return AppCompatResources.getDrawable(context, i2);
    }

    private static Drawable loadDrawableV4(Context context, int i2, Resources.Theme theme) {
        return ResourcesCompat.getDrawable(context.getResources(), i2, theme);
    }
}
