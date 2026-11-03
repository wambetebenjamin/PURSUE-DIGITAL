package com.google.common.collect;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class Platform {
    static void checkGwtRpcEnabled() {
    }

    static int reduceExponentIfGwt(int i2) {
        return i2;
    }

    static int reduceIterationsIfGwt(int i2) {
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Map<K, V> newHashMapWithExpectedSize(int i2) {
        return CompactHashMap.createWithExpectedSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int i2) {
        return CompactLinkedHashMap.createWithExpectedSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Set<E> newHashSetWithExpectedSize(int i2) {
        return CompactHashSet.createWithExpectedSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Set<E> newLinkedHashSetWithExpectedSize(int i2) {
        return CompactLinkedHashSet.createWithExpectedSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
        return CompactHashMap.create();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Set<E> preservesInsertionOrderOnAddsSet() {
        return CompactHashSet.create();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T[] newArray(T[] tArr, int i2) {
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T[] copy(Object[] objArr, int i2, int i3, T[] tArr) {
        return (T[]) Arrays.copyOfRange(objArr, i2, i3, tArr.getClass());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }

    private Platform() {
    }
}
