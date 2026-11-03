package com.bytedance.common.utility.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
/* compiled from: ArrayUtils.java */
/* loaded from: classes2.dex */
public final class a {
    private a() {
    }

    public static long[] toLongArray(List<Long> list) {
        if (list == null) {
            return null;
        }
        long[] jArr = new long[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            jArr[i2] = list.get(i2).longValue();
        }
        return jArr;
    }

    public static List<Long> toList(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (long j2 : jArr) {
            arrayList.add(Long.valueOf(j2));
        }
        return arrayList;
    }

    public static <T> T[] combineArray(T[] tArr, T[] tArr2) {
        T[] tArr3 = (T[]) copyOfRange(tArr, 0, tArr.length + tArr2.length);
        System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
        return tArr3;
    }

    public static <T> T[] insert(T[] tArr, int i2, T t) {
        T t2;
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), tArr.length + 1));
        int i3 = 0;
        while (i3 < tArr2.length) {
            if (i3 < i2) {
                t2 = tArr[i3];
            } else {
                t2 = i3 == i2 ? t : tArr[i3 - 1];
            }
            tArr2[i3] = t2;
            i3++;
        }
        return tArr2;
    }

    public static <T> T[] copyOfRange(T[] tArr, int i2, int i3) {
        int length = tArr.length;
        if (i2 <= i3) {
            if (i2 < 0 || i2 > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i4 = i3 - i2;
            int min = Math.min(i4, length - i2);
            T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i4));
            System.arraycopy(tArr, i2, tArr2, 0, min);
            return tArr2;
        }
        throw new IllegalArgumentException();
    }
}
