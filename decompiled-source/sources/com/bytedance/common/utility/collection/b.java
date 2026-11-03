package com.bytedance.common.utility.collection;

import java.util.Collection;
/* compiled from: CollectionUtils.java */
/* loaded from: classes2.dex */
public class b {
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static void clear(Object obj) {
        com.bytedance.common.utility.reflect.a.callMethod(obj, "clear", new Object[0]);
    }
}
