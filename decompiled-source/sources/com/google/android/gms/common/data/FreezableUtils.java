package com.google.android.gms.common.data;

import java.util.ArrayList;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public final class FreezableUtils {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        ArrayList<T> arrayList2 = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList2.add(arrayList.get(i2).freeze());
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        for (E e2 : iterable) {
            arrayList.add(e2.freeze());
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e2 : eArr) {
            arrayList.add(e2.freeze());
        }
        return arrayList;
    }
}
