package com.bytedance.common.utility;

import java.util.List;
/* compiled from: Lists.java */
/* loaded from: classes2.dex */
public final class i {
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean notEmpty(List<T> list) {
        return list != null && list.size() > 0;
    }

    public static <T> boolean deepEquals(List<T> list, List<T> list2) {
        if (list == list2) {
            return true;
        }
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!list.get(i2).equals(list2.get(i2))) {
                return false;
            }
        }
        return true;
    }
}
