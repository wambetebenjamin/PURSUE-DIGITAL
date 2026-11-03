package com.bytedance.push.utils;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
/* compiled from: SystemUtils.java */
/* loaded from: classes2.dex */
public class v {
    public static boolean a() {
        return "0".equals(SystemProperties.get("ro.adb.secure")) || "1".equals(SystemProperties.get("ro.debuggable"));
    }

    public static boolean a(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
    }
}
