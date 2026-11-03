package com.google.common.cache;
@ElementTypesAreNonnullByDefault
/* loaded from: classes6.dex */
public interface Weigher<K, V> {
    int weigh(K k, V v);
}
