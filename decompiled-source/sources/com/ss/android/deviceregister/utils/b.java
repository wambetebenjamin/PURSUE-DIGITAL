package com.ss.android.deviceregister.utils;

import android.text.TextUtils;
/* compiled from: DeviceRegistrationUtils.java */
/* loaded from: classes9.dex */
public class b {
    public static boolean isValidUDID(String str) {
        int length;
        if (str != null && (length = str.length()) >= 13 && length <= 160) {
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'f') && ((charAt < 'A' || charAt > 'F') && charAt != '-'))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("unknown") || str.equalsIgnoreCase("None")) {
            return true;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) != '0') {
                return false;
            }
        }
        return true;
    }
}
