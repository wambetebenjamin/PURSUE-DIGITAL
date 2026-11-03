package com.bytedance.bdinstall.util;

import android.content.Context;
import android.provider.Settings;
/* compiled from: HardwareUtils.java */
/* loaded from: classes.dex */
public class h {
    public static String a(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
