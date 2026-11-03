package com.bytedance.common.utility.android;

import android.content.Context;
/* compiled from: ManifestData.java */
/* loaded from: classes2.dex */
public class d {
    private static Object a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getString(Context context, String str) {
        Object a2 = a(context, str);
        if (a2 == null) {
            return null;
        }
        return (String) a2;
    }

    public static int getInt(Context context, String str) {
        Object a2 = a(context, str);
        if (a2 == null) {
            return -1;
        }
        return ((Integer) a2).intValue();
    }

    public static Boolean getBoolean(Context context, String str) {
        Object a2 = a(context, str);
        if (a2 == null) {
            return null;
        }
        return (Boolean) a2;
    }

    public static Object get(Context context, String str) {
        return a(context, str);
    }
}
