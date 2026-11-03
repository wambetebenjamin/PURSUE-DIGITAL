package com.bytedance.apm.util;

import java.util.Random;
/* compiled from: NumberUtils.java */
/* loaded from: classes.dex */
public class t {

    /* renamed from: a  reason: collision with root package name */
    private static Random f1715a;

    public static long safeUnbox(Long l) {
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public static String uuid() {
        if (f1715a == null) {
            f1715a = new Random(System.currentTimeMillis());
        }
        return Long.toHexString(f1715a.nextLong());
    }
}
