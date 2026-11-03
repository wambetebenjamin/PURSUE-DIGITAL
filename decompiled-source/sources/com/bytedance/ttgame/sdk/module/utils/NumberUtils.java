package com.bytedance.ttgame.sdk.module.utils;

import com.bytedance.ttgame.framework.module.util.StringUtil;
/* loaded from: classes8.dex */
public class NumberUtils {
    public static int getInteger(String str, int i2) {
        if (StringUtil.isNullOrEmpty(str)) {
            return i2;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i2;
        }
    }
}
