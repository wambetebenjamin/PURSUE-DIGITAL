package com.ss.thor;

import android.os.Build;
import android.text.TextUtils;
/* compiled from: Utils.java */
/* loaded from: classes9.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18792a = 1;

    /* renamed from: b  reason: collision with root package name */
    private static final int f18793b = 2;

    /* renamed from: c  reason: collision with root package name */
    private static final int f18794c = 3;

    /* renamed from: d  reason: collision with root package name */
    private static int f18795d = 3;

    /* renamed from: e  reason: collision with root package name */
    private static int f18796e = 3;

    /* renamed from: f  reason: collision with root package name */
    private static int f18797f = 3;

    public static boolean isOppo() {
        int i2 = f18795d;
        if (i2 != 3) {
            return i2 == 1;
        }
        String str = Build.BRAND;
        int i3 = (TextUtils.isEmpty(str) || !str.toLowerCase().contains("oppo")) ? 2 : 1;
        f18795d = i3;
        return i3 == 1;
    }

    public static boolean isSihi() {
        int i2 = f18796e;
        if (i2 != 3) {
            return i2 == 1;
        }
        String str = Build.HARDWARE;
        int i3 = (TextUtils.isEmpty(str) || !(str.toLowerCase().contains("hi") || str.toLowerCase().contains("kirin"))) ? 2 : 1;
        f18796e = i3;
        return i3 == 1;
    }

    public static boolean isSamsung() {
        int i2 = f18797f;
        if (i2 != 3) {
            return i2 == 1;
        }
        String str = Build.BRAND;
        int i3 = (TextUtils.isEmpty(str) || !str.toLowerCase().contains(com.bytedance.common.utility.e.ROM_SAMSUNG)) ? 2 : 1;
        f18797f = i3;
        return i3 == 1;
    }
}
