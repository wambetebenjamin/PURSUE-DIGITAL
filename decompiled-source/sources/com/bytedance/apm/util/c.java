package com.bytedance.apm.util;
/* compiled from: ArrayUtils.java */
/* loaded from: classes.dex */
public class c {
    public static int indexOfFirst(Object[] objArr, Class<?> cls) {
        if (objArr != null && objArr.length != 0) {
            int length = objArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (objArr[i2] != null && objArr[i2].getClass().equals(cls)) {
                    return i2;
                }
            }
        }
        return -1;
    }
}
