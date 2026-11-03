package com.bytedance.react.framework.utils.notchutil.impl;

import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import com.bytedance.react.framework.utils.notchutil.INotchScreen;
import com.bytedance.react.framework.utils.notchutil.utils.ScreenUtil;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class MiNotchScreen implements INotchScreen {
    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean isShowInNotch(Window window) {
        return true;
    }

    private static boolean isNotch() {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, "ro.miui.notch", 0)).intValue() == 1;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int getNotchHeight(Context context) {
        int identifier = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getNotchWidth(Context context) {
        int identifier = context.getResources().getIdentifier("notch_width", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean hasNotch(Context context) {
        return isNotch();
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public void setDisplayInNotch(Window window) {
        try {
            Window.class.getMethod("addExtraFlags", Integer.TYPE).invoke(window, 1792);
        } catch (Exception unused) {
        }
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public List<Rect> getNotchRect(Context context, Window window) {
        Rect calculateNotchRect = ScreenUtil.calculateNotchRect(context, getNotchWidth(context), getNotchHeight(context));
        ArrayList arrayList = new ArrayList();
        arrayList.add(calculateNotchRect);
        return arrayList;
    }
}
