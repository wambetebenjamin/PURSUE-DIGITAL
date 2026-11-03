package com.bytedance.common.jato.boost;

import android.os.Build;
import android.text.TextUtils;
import com.bytedance.common.utility.e;
import dalvik.annotation.optimization.FastNative;
/* loaded from: classes.dex */
public class DexCacheExpandOpt {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f4537a;

    @FastNative
    public static native void checkHitExpand();

    private static native boolean expandInner(ClassLoader classLoader, int i2, int i3, int i4, int i5);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static boolean a(int i2) {
        if (i2 <= 1024) {
            return false;
        }
        f4537a = true;
        return expandInner(DexCacheExpandOpt.class.getClassLoader(), i2, 0, 0, 0);
    }

    private static String a() {
        String str = Build.BRAND;
        String str2 = Build.MODEL;
        return TextUtils.isEmpty(str2) ? TextUtils.isEmpty(str) ? "unknown" : str : (str == null || str2.contains(str)) ? str2 : str + " " + str2;
    }

    public static boolean a(int i2, int i3, int i4, int i5) {
        String a2 = a();
        int i6 = 0;
        if (a2 == null) {
            return false;
        }
        String lowerCase = a2.toLowerCase();
        if (lowerCase.contains("aosp") || f4537a) {
            return false;
        }
        if (i2 <= 1024) {
            i2 = 0;
        }
        if (i3 <= 1024) {
            i3 = 0;
        }
        if (i4 <= 1024) {
            i4 = 0;
        }
        if (i5 <= 1024) {
            i5 = 0;
        }
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            return false;
        }
        if (i3 <= 0 || !lowerCase.contains(e.ROM_SAMSUNG)) {
            i6 = i3;
        }
        f4537a = true;
        return expandInner(DexCacheExpandOpt.class.getClassLoader(), i2, i6, i4, i5);
    }
}
