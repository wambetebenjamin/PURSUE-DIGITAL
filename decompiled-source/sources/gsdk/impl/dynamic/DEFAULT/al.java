package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import java.util.List;
/* compiled from: AndroidPNotchScreen.java */
/* loaded from: classes10.dex */
public class al implements aj {
    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public boolean a(Context context) {
        return true;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public List<Rect> a(Context context, Window window) {
        DisplayCutout cutout;
        if (window == null) {
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService(WindowManager.class);
            if (Build.VERSION.SDK_INT >= 30) {
                WindowInsets windowInsets = windowManager.getCurrentWindowMetrics().getWindowInsets();
                if (windowInsets != null) {
                    cutout = windowInsets.getDisplayCutout();
                }
                cutout = null;
            } else {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                if (Build.VERSION.SDK_INT >= 29) {
                    cutout = defaultDisplay.getCutout();
                }
                cutout = null;
            }
            if (cutout != null) {
                return cutout.getBoundingRects();
            }
            return null;
        }
        return b(window);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public boolean a(Window window) {
        return b(window) != null;
    }

    private List<Rect> b(Window window) {
        DisplayCutout displayCutout;
        WindowInsets rootWindowInsets = window.getDecorView().getRootWindowInsets();
        if (rootWindowInsets == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null) {
            return null;
        }
        return displayCutout.getBoundingRects();
    }
}
