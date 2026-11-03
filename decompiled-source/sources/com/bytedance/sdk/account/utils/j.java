package com.bytedance.sdk.account.utils;
/* compiled from: TypeUtils.java */
/* loaded from: classes2.dex */
public class j {
    public static long parseLong(String str, long j2) {
        if (str != null && !str.isEmpty()) {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException unused) {
            }
        }
        return j2;
    }
}
