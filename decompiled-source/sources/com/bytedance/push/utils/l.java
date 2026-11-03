package com.bytedance.push.utils;

import android.os.Build;
import android.text.TextUtils;
/* compiled from: PushUtils.java */
/* loaded from: classes2.dex */
public class l {
    public static void printStackTrace(Exception exc) {
        if (h.debug()) {
            exc.printStackTrace();
        }
    }

    public static boolean isBadDeviceId(String str) {
        if (com.bytedance.common.utility.o.isEmpty(str) || str.equalsIgnoreCase("unknown")) {
            return true;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) != '0') {
                return false;
            }
        }
        return true;
    }

    public static boolean isVivo() {
        String str = Build.BRAND;
        if (TextUtils.isEmpty(str) || !str.toLowerCase().contains("vivo")) {
            String str2 = Build.MODEL;
            if (TextUtils.isEmpty(str2) || !str2.toLowerCase().contains("vivo")) {
                String str3 = Build.MANUFACTURER;
                return !TextUtils.isEmpty(str3) && str3.toLowerCase().contains("vivo");
            }
            return true;
        }
        return true;
    }

    public static boolean isLollipopMr1() {
        return 22 == Build.VERSION.SDK_INT;
    }
}
