package com.bytedance.platform.godzilla.utils;

import android.os.Build;
/* compiled from: OSUtil.java */
/* loaded from: classes2.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11098a = "harmony";

    private f() {
    }

    public static boolean isAndroidPHigher() {
        return Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }

    public static boolean isHarmonyOS() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return f11098a.equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
