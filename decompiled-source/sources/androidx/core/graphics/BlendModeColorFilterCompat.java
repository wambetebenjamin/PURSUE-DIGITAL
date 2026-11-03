package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import androidx.core.graphics.BlendModeUtils;
/* loaded from: classes.dex */
public class BlendModeColorFilterCompat {
    public static ColorFilter createBlendModeColorFilterCompat(int i2, BlendModeCompat blendModeCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            Object obtainBlendModeFromCompat = BlendModeUtils.Api29Impl.obtainBlendModeFromCompat(blendModeCompat);
            if (obtainBlendModeFromCompat != null) {
                return Api29Impl.createBlendModeColorFilter(i2, obtainBlendModeFromCompat);
            }
            return null;
        }
        PorterDuff.Mode obtainPorterDuffFromCompat = BlendModeUtils.obtainPorterDuffFromCompat(blendModeCompat);
        if (obtainPorterDuffFromCompat != null) {
            return new PorterDuffColorFilter(i2, obtainPorterDuffFromCompat);
        }
        return null;
    }

    private BlendModeColorFilterCompat() {
    }

    /* loaded from: classes.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        static ColorFilter createBlendModeColorFilter(int i2, Object obj) {
            return new BlendModeColorFilter(i2, (BlendMode) obj);
        }
    }
}
