package com.bytedance.sync.util;

import android.text.TextUtils;
/* compiled from: UidHelper.java */
/* loaded from: classes3.dex */
public class c {
    public static boolean isSameUser(String str, String str2) {
        if (TextUtils.equals(str, str2)) {
            return true;
        }
        return a(str) && a(str2);
    }

    private static boolean a(String str) {
        return TextUtils.isEmpty(str) || TextUtils.equals(str, "0");
    }
}
