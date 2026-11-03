package com.bytedance.mira.plugin.hook.flipped.compat;

import android.util.Log;
import java.lang.reflect.Method;
/* loaded from: classes2.dex */
public class FlippedV2Impl implements c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9493a = "FlippedV2Impl";

    private native Method getDeclaredMethod(Object obj, String str, Class<?>[] clsArr);

    static {
        System.loadLibrary("flipped");
    }

    @Override // com.bytedance.mira.plugin.hook.flipped.compat.c
    public void a() {
        try {
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            Method declaredMethod = getDeclaredMethod(cls, "getRuntime", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Method declaredMethod2 = getDeclaredMethod(cls, "setHiddenApiExemptions", new Class[]{String[].class});
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(invoke, new String[]{"L"});
            Log.e(f9493a, "V2 invokeHiddenApiRestrictions success.");
        } catch (Exception e2) {
            Log.e(f9493a, "V2 invokeHiddenApiRestrictions fail: " + Log.getStackTraceString(e2));
        }
    }
}
