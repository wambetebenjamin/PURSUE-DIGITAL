package com.bytedance.framwork.core.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
/* compiled from: ListUtils.java */
/* loaded from: classes2.dex */
public class a {
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
}
