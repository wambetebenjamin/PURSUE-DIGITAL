package com.bytedance.react.framework.utils.notchutil.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.react.framework.utils.notchutil.INotchScreen;
import com.bytedance.react.framework.utils.notchutil.utils.ScreenUtil;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class HuaweiNotchScreen implements INotchScreen {
    public static final int FLAG_NOTCH_SUPPORT = 65536;

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean isShowInNotch(Window window) {
        return true;
    }

    public static void setNotDisplayInNotch(Activity activity) {
        try {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            cls.getMethod("clearHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 65536);
            window.getWindowManager().updateViewLayout(window.getDecorView(), window.getDecorView().getLayoutParams());
        } catch (Throwable unused) {
        }
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public boolean hasNotch(Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public void setDisplayInNotch(Window window) {
        try {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            cls.getMethod("addHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 65536);
            window.getWindowManager().updateViewLayout(window.getDecorView(), window.getDecorView().getLayoutParams());
        } catch (Throwable unused) {
        }
    }

    @Override // com.bytedance.react.framework.utils.notchutil.INotchScreen
    public List<Rect> getNotchRect(Context context, Window window) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            int[] iArr = (int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(ScreenUtil.calculateNotchRect(context, iArr[0], iArr[1]));
            return arrayList;
        } catch (Throwable unused) {
            return null;
        }
    }
}
