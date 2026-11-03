package com.bytedance.mira.plugin.hook.flipped.compat;

import android.util.Log;
import java.lang.reflect.Method;
/* compiled from: FlippedV1Impl.java */
/* loaded from: classes2.dex */
public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9494a = "FlippedV1Impl";

    @Override // com.bytedance.mira.plugin.hook.flipped.compat.c
    public void a() {
        try {
            Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            Method method = (Method) declaredMethod.invoke(cls, "getRuntime", new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[0]);
            Method method2 = (Method) declaredMethod.invoke(cls, "setHiddenApiExemptions", new Class[]{String[].class});
            method2.setAccessible(true);
            method2.invoke(invoke, new String[]{"L"});
            Log.e(f9494a, "V1 invokeHiddenApiRestrictions success.");
        } catch (Exception e2) {
            Log.e(f9494a, "V1 invokeHiddenApiRestrictions fail: " + Log.getStackTraceString(e2));
        }
    }
}
