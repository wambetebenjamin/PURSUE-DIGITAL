package com.bytedance.applog.sampling.util;
/* compiled from: TeaHashSamplingUtils.java */
/* loaded from: classes.dex */
public class c {
    public static boolean a(String str, long j2, long j3) {
        return j3 <= 0 || j3 > j2 || (a.a(str).longValue() >>> 32) % (j2 / j3) == 0;
    }
}
