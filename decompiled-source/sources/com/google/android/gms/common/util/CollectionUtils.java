package com.google.android.gms.common.util;

import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    @Deprecated
    public static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3) {
        Map zzb = zzb(3, false);
        zzb.put(k, v);
        zzb.put(k2, v2);
        zzb.put(k3, v3);
        return Collections.unmodifiableMap(zzb);
    }

    public static <K, V> Map<K, V> mapOfKeyValueArrays(K[] kArr, V[] vArr) {
        int length = kArr.length;
        int length2 = vArr.length;
        if (length != length2) {
            StringBuilder sb = new StringBuilder(String.valueOf(length).length() + 44 + String.valueOf(length2).length());
            sb.append("Key and values array lengths not equal: ");
            sb.append(length);
            sb.append(" != ");
            sb.append(length2);
            throw new IllegalArgumentException(sb.toString());
        } else if (length == 0) {
            return Collections.emptyMap();
        } else {
            if (length == 1) {
                return Collections.singletonMap(kArr[0], vArr[0]);
            }
            Map zzb = zzb(length, false);
            for (int i2 = 0; i2 < kArr.length; i2++) {
                zzb.put(kArr[i2], vArr[i2]);
            }
            return Collections.unmodifiableMap(zzb);
        }
    }

    public static <T> Set<T> mutableSetOfWithSize(int i2) {
        if (i2 == 0) {
            return new ArraySet();
        }
        return zza(i2, true);
    }

    @Deprecated
    public static <T> Set<T> setOf(T t, T t2, T t3) {
        Set zza = zza(3, false);
        zza.add(t);
        zza.add(t2);
        zza.add(t3);
        return Collections.unmodifiableSet(zza);
    }

    private static Map zzb(int i2, boolean z) {
        if (i2 <= 256) {
            return new ArrayMap(i2);
        }
        return new HashMap(i2, 1.0f);
    }

    @Deprecated
    public static <T> List<T> listOf(T t) {
        return Collections.singletonList(t);
    }

    private static Set zza(int i2, boolean z) {
        if (i2 > (true != z ? 256 : 128)) {
            return new HashSet(i2, true != z ? 1.0f : 0.75f);
        }
        return new ArraySet(i2);
    }

    @Deprecated
    public static <T> List<T> listOf(T... tArr) {
        int length = tArr.length;
        if (length != 0) {
            if (length == 1) {
                return Collections.singletonList(tArr[0]);
            }
            return Collections.unmodifiableList(Arrays.asList(tArr));
        }
        return Collections.emptyList();
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        Map zzb = zzb(6, false);
        zzb.put(k, v);
        zzb.put(k2, v2);
        zzb.put(k3, v3);
        zzb.put(k4, v4);
        zzb.put(k5, v5);
        zzb.put(k6, v6);
        return Collections.unmodifiableMap(zzb);
    }

    @Deprecated
    public static <T> Set<T> setOf(T... tArr) {
        int length = tArr.length;
        if (length != 0) {
            if (length != 1) {
                if (length == 2) {
                    T t = tArr[0];
                    T t2 = tArr[1];
                    Set zza = zza(2, false);
                    zza.add(t);
                    zza.add(t2);
                    return Collections.unmodifiableSet(zza);
                } else if (length != 3) {
                    if (length == 4) {
                        T t3 = tArr[0];
                        T t4 = tArr[1];
                        T t5 = tArr[2];
                        T t6 = tArr[3];
                        Set zza2 = zza(4, false);
                        zza2.add(t3);
                        zza2.add(t4);
                        zza2.add(t5);
                        zza2.add(t6);
                        return Collections.unmodifiableSet(zza2);
                    }
                    Set zza3 = zza(length, false);
                    Collections.addAll(zza3, tArr);
                    return Collections.unmodifiableSet(zza3);
                } else {
                    return setOf(tArr[0], tArr[1], tArr[2]);
                }
            }
            return Collections.singleton(tArr[0]);
        }
        return Collections.emptySet();
    }
}
