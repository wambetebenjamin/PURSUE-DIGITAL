package com.bytedance.react.framework.utils;

import android.text.TextUtils;
import com.bytedance.react.framework.core.BRNManager;
/* loaded from: classes2.dex */
public class VersionCompareUtils {
    public static boolean checkModuleVersionValid(String str, String str2, String str3) {
        String[] versionCode = BRNManager.newInstance().getVersionCode(str2, str3);
        if (versionCode != null && versionCode.length == 2) {
            String str4 = versionCode[0];
            String str5 = versionCode[1];
            if (!TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5) && (compareVersion(str, str4) < 0 || compareVersion(str, str5) > 0)) {
                return false;
            }
        }
        return true;
    }

    public static int compareVersion(String str, String str2) {
        try {
            if (str.equals(str2)) {
                return 0;
            }
            String[] split = str.split("[._]");
            String[] split2 = str2.split("[._]");
            int min = Math.min(split.length, split2.length);
            int i2 = 0;
            long j2 = 0;
            while (i2 < min) {
                j2 = Long.parseLong(split[i2]) - Long.parseLong(split2[i2]);
                if (j2 != 0) {
                    break;
                }
                i2++;
            }
            int i3 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
            if (i3 != 0) {
                return i3 > 0 ? 1 : -1;
            }
            for (int i4 = i2; i4 < split.length; i4++) {
                if (Long.parseLong(split[i4]) > 0) {
                    return 1;
                }
            }
            while (i2 < split2.length) {
                if (Long.parseLong(split2[i2]) > 0) {
                    return -1;
                }
                i2++;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }
}
