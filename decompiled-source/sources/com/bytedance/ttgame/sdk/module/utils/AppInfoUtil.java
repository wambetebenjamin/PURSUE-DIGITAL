package com.bytedance.ttgame.sdk.module.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
/* loaded from: classes8.dex */
public class AppInfoUtil {
    public static String getAppVersionName(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int i2 = packageInfo.applicationInfo.labelRes;
            if (i2 != 0) {
                return context.getResources().getString(i2);
            }
            CharSequence charSequence = packageInfo.applicationInfo.nonLocalizedLabel;
            return charSequence != null ? charSequence.toString() : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
