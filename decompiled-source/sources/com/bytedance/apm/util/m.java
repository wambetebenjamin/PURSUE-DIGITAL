package com.bytedance.apm.util;

import android.util.Pair;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: ListUtils.java */
/* loaded from: classes.dex */
public class m {

    /* compiled from: ListUtils.java */
    /* loaded from: classes.dex */
    public interface a<A, B> {
        boolean equals(A a2, B b2);
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static String[] toArray(Set<String> set) {
        String[] strArr = new String[set.size()];
        int i2 = 0;
        for (String str : set) {
            strArr[i2] = str;
            i2++;
        }
        return strArr;
    }

    public static String listToString(Collection collection, String str) {
        if (collection == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : collection) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    public static String arrayToString(Object[] objArr, String str) {
        if (objArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : objArr) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    public static Map<String, String> listToMap(List<Pair<String, String>> list) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (Pair<String, String> pair : list) {
            if (pair != null && pair.first != null) {
                hashMap.put(pair.first, pair.second);
            }
        }
        return hashMap;
    }

    public static <L, O> boolean removeAll(Collection<L> collection, O o, a<? super L, O> aVar) {
        Iterator<L> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (aVar.equals(it.next(), o)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }
}
