package com.bytedance.frameworks.gpm.util;

import android.content.res.Resources;
import android.os.Build;
import com.bytedance.librarian.c;
/* compiled from: DeviceUtil.java */
/* loaded from: classes2.dex */
public class d {
    public static String getOS() {
        return com.bytedance.bdturing.utils.b.f4370d;
    }

    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getOSVersion() {
        String str = Build.VERSION.RELEASE;
        return str.contains(c.a.DOT) ? str : str + ".0";
    }

    public static String getResolution() {
        return Resources.getSystem().getDisplayMetrics().widthPixels + "*" + Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
