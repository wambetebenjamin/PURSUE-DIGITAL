package com.bytedance.ttgame.sdk.module.utils;

import com.bytedance.librarian.b;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes8.dex */
public class ConstManager {
    public static int IVPARAMETER = 1;
    public static int SKEY;
    private static ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

    private static native String getConst(int i2);

    static {
        b.loadLibrary("gsdk_base");
    }

    public static String getConstValue(int i2) {
        if (map.get(Integer.valueOf(i2)) != null) {
            return map.get(Integer.valueOf(i2));
        }
        String str = getConst(i2);
        map.put(Integer.valueOf(i2), str);
        return str;
    }
}
