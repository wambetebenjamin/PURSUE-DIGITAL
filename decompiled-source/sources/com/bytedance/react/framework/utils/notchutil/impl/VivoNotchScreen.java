package com.bytedance.react.framework.utils.notchutil.impl;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.Window;
import com.bytedance.react.framework.utils.notchutil.INotchScreen;
import com.bytedance.react.framework.utils.notchutil.utils.ScreenUtil;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class VivoNotchScreen implements INotchScreen {
    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean isShowInNotch(Window window) {
        return true;
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    @Deprecated
    public void setDisplayInNotch(Window window) {
    }

    public static boolean isNotch() {
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            return ((Boolean) cls.getMethod("isFtFeatureSupport", Integer.TYPE).invoke(cls.newInstance(), 32)).booleanValue();
        } catch (Exception e2) {
            Log.e("tag", "get error() ", e2);
            return false;
        }
    }

    public static int getNotchHeight(Context context) {
        return (int) (getDensity(context) * 27.0f);
    }

    public static int getNotchWidth(Context context) {
        return (int) (getDensity(context) * 100.0f);
    }

    private static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean hasNotch(Context context) {
        return isNotch();
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public List<Rect> getNotchRect(Context context, Window window) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ScreenUtil.calculateNotchRect(context, getNotchWidth(context), getNotchHeight(context)));
        return arrayList;
    }
}
