package com.bytedance.applog.monitor.v3;

import java.security.SecureRandom;
/* compiled from: RandomUtils.java */
/* loaded from: classes.dex */
public class j {
    public static boolean a(int i2, int i3) {
        if (i2 <= 0) {
            return false;
        }
        return i2 >= i3 || ((long) new SecureRandom().nextInt(i3)) < ((long) i2);
    }
}
