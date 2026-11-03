package com.bytedance.react.framework.utils.notchutil.impl;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.bytedance.react.framework.utils.notchutil.INotchScreen;
import java.util.List;
/* loaded from: classes2.dex */
public class AndroidPNotchScreen implements INotchScreen {
    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean hasNotch(Context context) {
        return true;
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public void setDisplayInNotch(Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public List<Rect> getNotchRect(Context context, Window window) {
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
        return getWindowDisplayCutout(window);
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean isShowInNotch(Window window) {
        return getWindowDisplayCutout(window) != null;
    }

    private List<Rect> getWindowDisplayCutout(Window window) {
        DisplayCutout displayCutout;
        WindowInsets rootWindowInsets = window.getDecorView().getRootWindowInsets();
        if (rootWindowInsets == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null) {
            return null;
        }
        return displayCutout.getBoundingRects();
    }
}
