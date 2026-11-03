package com.bytedance.frameworks.gpm.util;

import android.content.Context;
import android.content.pm.PackageManager;
/* compiled from: APPUtil.java */
/* loaded from: classes2.dex */
public class a {
    public static String getSDKVersion() {
        return com.bytedance.frameworks.gpm.a.VERSION_NAME;
    }

    public static int getBuildVersion(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static String getAppVersion(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }
}
