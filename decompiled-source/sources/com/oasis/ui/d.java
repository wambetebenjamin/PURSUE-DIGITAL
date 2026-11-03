package com.oasis.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
/* loaded from: classes9.dex */
final class d implements e {
    @Override // com.oasis.ui.e
    public final boolean a() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @Override // com.oasis.ui.e
    public final boolean a(Context context) {
        Log.i("MeizuNotch", "SDK_VERSION:" + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= 17) {
            int i2 = Settings.Global.getInt(context.getContentResolver(), "mz_fringe_hide", 0);
            Log.i("MeizuNotch", "fringeHide:".concat(String.valueOf(i2)));
            if (i2 == 1) {
                return false;
            }
        }
        try {
            return ((Boolean) Class.forName("flyme.config.FlymeFeature").getDeclaredField("IS_FRINGE_DEVICE").get(null)).booleanValue();
        } catch (Exception e2) {
            Log.e("MeizuNotch", "hasNotch throw exception.", e2);
            return false;
        }
    }

    @Override // com.oasis.ui.e
    public final boolean b() {
        return false;
    }

    @Override // com.oasis.ui.e
    public final int[] b(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("fringe_height", "dimen", "android");
        if (identifier > 0) {
            return g.a(resources.getDimensionPixelSize(identifier));
        }
        return null;
    }
}
