package com.bytedance.common.jato.memory;

import android.os.Build;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
/* loaded from: classes.dex */
public class StringCacheOpt {
    private static native void doStringCache(boolean z, String[] strArr, String[] strArr2, String[] strArr3);

    public static void a(boolean z, String[] strArr, String[] strArr2, String[] strArr3, boolean z2) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        com.bytedance.common.jato.d.loadLibrary();
        a();
        try {
            if (Build.VERSION.SDK_INT <= 30 && z2) {
                if (strArr2 != null) {
                    if (strArr == null) {
                        strArr = strArr2;
                    } else {
                        HashSet hashSet = new HashSet(Arrays.asList(strArr2));
                        hashSet.addAll(Arrays.asList(strArr));
                        strArr = (String[]) hashSet.toArray(new String[0]);
                    }
                }
                doStringCache(z, null, strArr, strArr3);
                return;
            }
            doStringCache(z, strArr, strArr2, strArr3);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void a() {
        System.out.println("Init string jni funcs, content: bH" + new String("H".getBytes(), StandardCharsets.UTF_8) + new String("ab"));
    }

    public static void a(boolean z, String[] strArr, String[] strArr2, String[] strArr3) {
        a(z, strArr, strArr2, strArr3, true);
    }
}
