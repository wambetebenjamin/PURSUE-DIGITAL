package com.bytedance.praisedialoglib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
/* compiled from: PraiseDialogSdkUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11180a = "PraiseDialogSdkUtils";

    /* renamed from: b  reason: collision with root package name */
    private static final int f11181b = 5020;

    public static boolean a(Context context, String str) {
        if (context == null) {
            return false;
        }
        PackageInfo packageInfo = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            } catch (Exception unused) {
                return false;
            }
        }
        return packageInfo != null && packageInfo.applicationInfo.enabled;
    }

    public static boolean a(Context context) {
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(com.bytedance.praisedialoglib.b.VIVO_APP_STORE_PKG_NAME, 0);
                int i2 = packageInfo.versionCode;
                String str = packageInfo.versionName;
                long j2 = 0;
                Log.d(f11180a, "versionCode = " + packageInfo.versionCode);
                Log.d(f11180a, "versionName = " + packageInfo.versionName);
                if (Build.VERSION.SDK_INT >= 28) {
                    j2 = packageInfo.getLongVersionCode();
                    Log.d(f11180a, "longVersionCode = " + packageInfo.getLongVersionCode());
                }
                return i2 >= f11181b || ((int) j2) >= f11181b;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }
}
