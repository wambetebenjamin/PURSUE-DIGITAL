package com.bytedance.apm.block;

import android.os.Build;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
/* compiled from: GFXInfo.java */
/* loaded from: classes.dex */
public class i {
    public static void getDisplayList() {
        Class<?> cls;
        try {
            Class<?> cls2 = Class.forName("android.view.WindowManagerGlobal");
            Object invoke = cls2.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls2.getDeclaredField("mRoots");
            declaredField.setAccessible(true);
            ArrayList arrayList = (ArrayList) declaredField.get(invoke);
            Field declaredField2 = Class.forName("android.view.ViewRootImpl").getDeclaredField("mView");
            declaredField2.setAccessible(true);
            Field declaredField3 = View.class.getDeclaredField("mRenderNode");
            declaredField3.setAccessible(true);
            if (Build.VERSION.SDK_INT > 28) {
                cls = Class.forName("android.graphics.RenderNode");
            } else {
                cls = Class.forName("android.view.RenderNode");
            }
            Method reflectHideMethod = com.bytedance.apm.block.trace.g.reflectHideMethod(cls, "output", new Class[0]);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                reflectHideMethod.invoke(declaredField3.get(declaredField2.get(arrayList.get(i2))), new Object[0]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
