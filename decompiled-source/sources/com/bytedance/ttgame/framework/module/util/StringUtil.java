package com.bytedance.ttgame.framework.module.util;
/* loaded from: classes3.dex */
public class StringUtil {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean isAllNullOrEmpty(String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (!isNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllNotNullOrEmpty(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return false;
        }
        for (String str : strArr) {
            if (isNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static long toLong(String str, long j2) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return j2;
        }
    }
}
